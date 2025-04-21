package com.example.jugangmate.form;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String userRole;
}
