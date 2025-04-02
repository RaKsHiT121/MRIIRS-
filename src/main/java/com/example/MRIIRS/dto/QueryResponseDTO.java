package com.example.MRIIRS.dto;

public class QueryResponseDTO {
    private int queryId;
    private String response;
    private String teacherName;

    public QueryResponseDTO(int queryId, String response, String teacherName) {
        this.queryId = queryId;
        this.response = response;
        this.teacherName = teacherName;
    }

    // Getters and Setters
    public int getQueryId() {
        return queryId;
    }

    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
