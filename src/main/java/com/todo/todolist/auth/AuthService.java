package com.todo.todolist.auth;

//import com.todo.todolist.CustomExceptions.UserAlreadyExistsException;
import com.todo.todolist.config.JWTService;
import com.todo.todolist.domain.User;
import com.todo.todolist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest){
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .email(registerRequest.getEmail())
                .lastName(registerRequest.getLastName())
                .location(registerRequest.getLocation())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .created_at(LocalDateTime.now())
                .build();


        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthResponse.builder().acessToken(token).build();

    }

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().acessToken(jwtToken).build();
    }
}
