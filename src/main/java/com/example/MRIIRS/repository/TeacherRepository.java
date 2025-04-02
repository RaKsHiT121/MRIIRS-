package com.example.MRIIRS.repository;

import com.example.MRIIRS.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Fetch the teacher with the least active queries in the given department
    @Query(value = "SELECT * FROM teacher WHERE department_id = :departmentId ORDER BY active_queries ASC LIMIT 1", nativeQuery = true)
    Optional<Teacher> findTeacherWithLeastActiveQueries(@Param("departmentId") Long departmentId);

    Optional<Teacher> findById(Long teacherId);
}
// Repository Layer - TeacherRepository.java
