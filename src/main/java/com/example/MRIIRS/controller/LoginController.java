
package com.example.MRIIRS.controller;

import com.example.MRIIRS.entity.LoginCredentials;
import com.example.MRIIRS.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        Optional<LoginCredentials> user = loginRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        
        if (user.isPresent()) {
            return new LoginResponse(true, user.get().getRole());
        } else {
            return new LoginResponse(false, "Invalid credentials");
        }
    }
}

class LoginRequest {
    private String username;
    private String password;
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class LoginResponse {
    private boolean success;
    private String role;

    public LoginResponse(boolean success, String role) {
        this.success = success;
        this.role = role;
    }

    public boolean isSuccess() { return success; }
    public String getRole() { return role; }
}