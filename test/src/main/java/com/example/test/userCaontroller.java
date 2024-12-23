package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class userCaontroller {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }


    @GetMapping("/login")
    public User loginUser(@RequestParam String username, @RequestParam String passwordHash) {
        if (Objects.requireNonNull(userRepository.findById(username).orElse(null)).getPassword().equals(passwordHash)) {
            return userRepository.findById(username).orElse(null);
        }

      return null;
    }

    @PostMapping("/add-user")
    public void register(@RequestParam String username, @RequestParam String name,
                         @RequestParam String password, @RequestParam String email) {
        if (userRepository.findById(username).orElse(null) != null) {
            throw new RuntimeException("Username already exists");
        }
        userRepository.save(new User(username, name, password, email));
    }
}