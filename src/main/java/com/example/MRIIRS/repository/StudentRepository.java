package com.example.MRIIRS.repository;

import com.example.MRIIRS.entity.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Optional<Student> findByEmail(String email); // Assuming email is used as username

    @Query("SELECT s.sId FROM Student s WHERE s.username = :username")
Long findStudentIdByUsername(@Param("username") String username);

}
