package dev.user.controller;

import dev.user.model.User;
import dev.user.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Boolean> getExistsUser(@PathVariable @NonNull String email) {
        if (service.existsByEmail(email)) {
            return ResponseEntity.ok(true);
        } else return ResponseEntity.ok(false);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        User u = service.addUser(user);
        if (u != null) {
            return ResponseEntity.ok(u);
        } else return ResponseEntity.badRequest().body("User already exists.");
    }
}
