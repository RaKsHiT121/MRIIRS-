package com.example.MRIIRS.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "active_queries", nullable = false)
    private int activeQueries = 0;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;
    
    public Long getTId(){
        return id;
    }
}
