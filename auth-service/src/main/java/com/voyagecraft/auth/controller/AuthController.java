package com.voyagecraft.auth.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.*;

import com.voyagecraft.auth.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    // Temporary storage for registered users
    private final Map<String, String> users = new ConcurrentHashMap<>();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    @PostMapping("/register")
    public Map<String, String> register(
            @RequestBody Map<String, String> user) {

        String username = user.get("username");
        String password = user.get("password");

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {

            return Map.of(
                    "message", "Username and password are required"
            );
        }

        if (users.containsKey(username)) {

            return Map.of(
                    "message", "Username already exists"
            );
        }

        users.put(username, password);

        return Map.of(
                "message", "Registration successful",
                "username", username
        );
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> user) {

        String username = user.get("username");
        String password = user.get("password");

        if (users.containsKey(username)
                && users.get(username).equals(password)) {

            String token = jwtUtil.generateToken(username);

            return Map.of(
                    "message", "Login successful",
                    "username", username,
                    "token", token
            );
        }

        return Map.of(
                "message", "Invalid username or password"
        );
    }

    // LOGOUT
    @PostMapping("/logout")
    public Map<String, String> logout() {

        return Map.of(
                "message", "Logout successful"
        );
    }

    // VIEW ALL REGISTERED USERS
    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {

        return Map.of(
                "totalUsers", users.size(),
                "users", users.keySet()
        );
    }
}