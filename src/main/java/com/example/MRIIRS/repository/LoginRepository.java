package com.example.MRIIRS.repository;

import com.example.MRIIRS.entity.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginCredentials, Long> {
    Optional<LoginCredentials> findByUsernameAndPassword(String username, String password);
}