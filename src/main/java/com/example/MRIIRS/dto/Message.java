package com.example.MRIIRS.dto;

public class Message {
    private String sender;
    private String departmentId; 
    private String message;

    public Message() {}

    public Message(String sender, String departmentId, String message) {
        this.sender = sender;
        this.departmentId = departmentId;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
