package com.example.MRIIRS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MRIIRS.entity.Department;
import com.example.MRIIRS.repository.DepartmentRepository;

import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "*") // Allow requests from frontend
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
