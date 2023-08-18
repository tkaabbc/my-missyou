package com.example.mymissyou.service;

import com.example.mymissyou.model.User;
import com.example.mymissyou.repository.UserRepository;
import com.example.mymissyou.util.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return JwtToken.createToken(user.getUsername());
        }
        // TODO optimize by send validating result to font-end
        return null;
    }
}
