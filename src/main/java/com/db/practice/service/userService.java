package com.db.practice.service;

import com.db.practice.model.User;
import com.db.practice.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    public userRepo repo;

    public User addUser(User u)
    {
        return repo.save(u);

    }
    public List<User> addUsers(List<User> users) {
        return repo.saveAll(users);
    }
    public List<User> getUsers()
    {
        return repo.findAll();
    }
    public User getUserById(int id)
    {
        return repo.getReferenceById(id);
    }
    public String deleteById(int id)
    {
        repo.deleteById(id);
        return "User with id "+id+" Deleted";
    }


}
