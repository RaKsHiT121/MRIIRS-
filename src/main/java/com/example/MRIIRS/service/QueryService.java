package com.example.MRIIRS.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.MRIIRS.entity.Teacher;
import com.example.MRIIRS.entity.query;
import com.example.MRIIRS.entity.query.Status;
import com.example.MRIIRS.repository.QueryRepository;
import com.example.MRIIRS.repository.TeacherRepository;

@Service
public class QueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
     @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    // Method to assign query to the least active teacher in a department
    public query assignQuery(Long studentId, Long departmentId, String description) {
        // Fetch the teacher with the least active queries in the department
        Teacher assignedTeacher = teacherRepository.findTeacherWithLeastActiveQueries(departmentId)
                .orElseThrow(() -> new RuntimeException("No available teacher found for this department!"));

        // Create a new query
        query query = new query();
        query.setStudentId(studentId);
        query.setTeacherId(assignedTeacher.getTId());
        query.setDescription(description);
        query.setStatus(Status.PENDING);
        query.setCreatedAt(LocalDateTime.now());
        query.setUpdatedAt(LocalDateTime.now());

        // Save the query in the database
       // queryRepository.save(query);

        // Increase the teacher's active query count
        assignedTeacher.setActiveQueries(assignedTeacher.getActiveQueries() + 1);
        teacherRepository.save(assignedTeacher);

        return query;
    }

    // Method to add a new query after checking the limits
    public String addQuery(int studentId, int departmentId, String description) {
        // Check the daily limit for the student
        String checkDailyLimitSql = "SELECT COUNT(*) FROM query WHERE student_id = ? AND DATE(created_at) = CURRENT_DATE";
        int dailyCount = jdbcTemplate.queryForObject(checkDailyLimitSql, Integer.class, studentId);

        // Check the monthly limit for the student
        String checkMonthlyLimitSql = "SELECT COUNT(*) FROM query WHERE student_id = ? AND MONTH(created_at) = MONTH(CURRENT_DATE) AND YEAR(created_at) = YEAR(CURRENT_DATE)";
        int monthlyCount = jdbcTemplate.queryForObject(checkMonthlyLimitSql, Integer.class, studentId);

        if (dailyCount >= 3) {
            return "Daily query limit exceeded!";
        }

        if (monthlyCount >= 10) {
            return "Monthly query limit exceeded!";
        }

        // Get the teacher with the least active queries in the department
        int teacherId = getTeacherWithLeastQueries(departmentId);

        if (teacherId == -1) {
            return "No teacher available in the department!";
        }

        // Insert the new query into the database
        String insertQuerySql = "INSERT INTO query (student_id, teacher_id, description, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, 'pending', NOW(), NOW())";
        jdbcTemplate.update(insertQuerySql, studentId, teacherId, description);

        // Update the teacher's active queries count
        updateTeacherActiveQueries(teacherId);

        return "Query submitted successfully!";
    }

    // Method to get teacher with least active queries in a department
    public int getTeacherWithLeastQueries(int departmentId) {
        String sql = "SELECT t_id FROM teacher WHERE department_id = ? ORDER BY active_queries ASC LIMIT 1";
        Integer teacherId = jdbcTemplate.queryForObject(sql, Integer.class, departmentId);

        // If no teacher is found
        return teacherId != null ? teacherId : -1;
    }

    // Method to update the teacher's active queries count
    public void updateTeacherActiveQueries(int teacherId) {
        String updateTeacherSql = "UPDATE teacher SET active_queries = active_queries + 1 WHERE t_id = ?";
        jdbcTemplate.update(updateTeacherSql, teacherId);
    }

    // Method to update the status of a query and assign it to the teacher
    public void assignQueryToTeacher(int queryId, int teacherId) {
        String updateQuerySql = "UPDATE query SET teacher_id = ?, status = 'assigned', updated_at = NOW() WHERE q_id = ?";
        jdbcTemplate.update(updateQuerySql, teacherId, queryId);

        // Update teacher's active queries count
        updateTeacherActiveQueries(teacherId);
    }

    // Method to update the query status (e.g., marked as resolved)
    public void resolveQuery(int queryId) {
        String updateQueryStatusSql = "UPDATE query SET status = 'resolved', updated_at = NOW() WHERE q_id = ?";
        jdbcTemplate.update(updateQueryStatusSql, queryId);
    }
}
