package com.example.jugangmate.controller;

import com.example.jugangmate.entity.User;
import com.example.jugangmate.form.UserForm;
import com.example.jugangmate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(UserForm userForm) {
        try {
            userService.userSignup(userForm);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> masterLogin(UserForm userForm) {
        try {
            User user = userService.userLogin(userForm);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
    }

    @GetMapping("/jwks")
    public ResponseEntity<Map<String, Object>> jwks() {

        Map<String, Object> jwkSet = userService.getJwks();

        return ResponseEntity.ok()
                .body(jwkSet);
    }

}
