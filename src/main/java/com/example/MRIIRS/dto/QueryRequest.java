package com.example.MRIIRS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {
    private Long studentId;
    private Long departmentId;
    private String description;
}
