package com.example.MRIIRS.controller;

import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MRIIRS.repository.QueryRepository;
import com.example.MRIIRS.entity.query;@RestController
@RequestMapping("/api")
public class QueryController {

    @Autowired
    private QueryRepository queryRepository;

    @GetMapping("/getStudentQueries")
    public ResponseEntity<?> getStudentQueries(@RequestParam Long studentId) {
        try {
            List<query> queries = queryRepository.findByStudentId(studentId);
            return ResponseEntity.ok(queries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving queries");
        }
    }
}
