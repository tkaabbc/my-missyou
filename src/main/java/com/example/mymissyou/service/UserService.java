package com.example.mymissyou.service;

import com.example.mymissyou.model.User;
import com.example.mymissyou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findFirstById(id);
    }
}

