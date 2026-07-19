package com.db.practice.controller;

import com.db.practice.model.User;
import com.db.practice.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    public userService service;
    @PostMapping("/addusers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return service.addUsers(users);
    }
    @PostMapping("/adduser")
    public User addUser(@RequestBody User u)
    {
        return service.addUser(u);
    }
    @GetMapping
    public List<User> getAllUsers()
    {
        return service.getUsers();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id)
    {

        return service.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id)
    {

        return service.deleteById(id);
    }
}
