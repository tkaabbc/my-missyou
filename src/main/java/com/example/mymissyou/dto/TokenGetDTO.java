package com.example.mymissyou.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenGetDTO {
    @NotBlank(message = "username不允许为空")
    private String username;
    @NotBlank(message = "password不允许为空")
    private String password;
}
