package com.todo.todolist.auth;

import com.todo.todolist.Enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/user/register")
    public ResponseEntity<?> userRegister(@RequestBody RegisterRequest registerRequest){

            registerRequest.setRole(Role.CUSTOMER);
            AuthResponse authResponse = authService.register(registerRequest);
            return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> adminRegister(@RequestBody RegisterRequest registerRequest){

            registerRequest.setRole(Role.ADMIN);
            AuthResponse authResponse = authService.register(registerRequest);
            return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }
}
