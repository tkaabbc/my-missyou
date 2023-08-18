package com.example.mymissyou.api.v1;

import com.example.mymissyou.dto.TokenGetDTO;
import com.example.mymissyou.interceptors.Whitelist;
import com.example.mymissyou.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @Whitelist
    public Map<String, String> login(
            @RequestBody @Validated TokenGetDTO userDate
            ) {
        String token = authenticationService.authenticate(userDate.getUsername(), userDate.getPassword());
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
