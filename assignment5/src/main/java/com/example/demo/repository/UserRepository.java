package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}