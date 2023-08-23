package com.example.mymissyou.repository;

import com.example.mymissyou.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findFirstById(Long id);
}
