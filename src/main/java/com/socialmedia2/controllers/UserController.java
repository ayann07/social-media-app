package com.socialmedia2.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialmedia2.models.User;
import com.socialmedia2.repository.UserRepository;
import com.socialmedia2.services.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable Integer userId)throws Exception{

        return userService.findUserById(userId);
    }

    @PutMapping("/api/users")
    public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws Exception{
        
        User reqUser=userService.findUserByJwt(jwt);
        User updatedUser=userService.updateUser(user,reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2){

        User reqUser=userService.findUserByJwt(jwt);
        User user=userService.followUser(reqUser.getId(), userId2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query")String query){

        List<User>users=userService.searchUser(query);
        return users;

    }
    
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        
        User user=userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

    
}