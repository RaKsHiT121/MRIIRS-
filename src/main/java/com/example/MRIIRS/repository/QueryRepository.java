package com.example.MRIIRS.repository;

import com.example.MRIIRS.entity.Student;
import com.example.MRIIRS.entity.Teacher;
import com.example.MRIIRS.entity.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface QueryRepository extends JpaRepository<query, Long> {
    List<query> findByStudentId(Long studentId);
}





