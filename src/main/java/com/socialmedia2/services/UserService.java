package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.User;

public interface UserService {
    
    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer userId1, Integer userId2);

    public User updateUser(User user,Integer userId) throws Exception;
    
    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);

}