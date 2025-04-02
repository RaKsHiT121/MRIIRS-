package com.example.MRIIRS.dto;

import lombok.Data;

@Data
public class QueryMessage {
    private String sender;
    private Long departmentId;  // ID of the selected department
    private String message;
}
