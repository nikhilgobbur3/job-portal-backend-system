package com.example.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String phone;

    private String password; // for register/update only
}
