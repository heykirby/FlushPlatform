package com.pku.edu.backend.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pku.edu.backend.mapper.UserMapper;
import com.pku.edu.backend.model.User;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @GetMapping
    public List<User> findAll() {

        return userMapper.selectAll();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userMapper.selectById(id);
    }
    @PostMapping
    public int insert(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @PutMapping
    public int update(@RequestBody User user) {
        return userMapper.update(user);
    }

    @DeleteMapping("/{id}")
    public int  delete(@PathVariable Long id) {
        return userMapper.delete(id);
    }

}
