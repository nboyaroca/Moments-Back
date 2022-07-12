package com.factoria.moments.controllers;

import com.factoria.moments.models.Moment;
import com.factoria.moments.models.User;
import com.factoria.moments.services.userS.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    // ATRIBUTS
    private IUserService userService;

    // CONSTRUCTOR
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // Get all users endpoint
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
