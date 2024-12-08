package com.socialmedia2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.config.JwtProvider;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Integer userId) throws Exception {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User does not exist with given id " + userId);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) {

        Optional<User> reqUserOpt = userRepository.findById(userId1);
        Optional<User> user2Opt = userRepository.findById(userId2);

        if (reqUserOpt.isPresent() && user2Opt.isPresent()) {
            User reqUser = reqUserOpt.get();
            User user2 = user2Opt.get();

            user2.getFollowers().add(userId1);
            reqUser.getFollowings().add(userId2);

            userRepository.save(reqUser);
            userRepository.save(user2);

            return reqUser;
        } else {
            throw new IllegalArgumentException("One or both users not found");
        }
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {

        Optional<User> up = userRepository.findById(userId);
        if (up.isEmpty()) {
            throw new Exception("User does not exist");
        }

        User oldUser = up.get();

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }
        User savedUser = userRepository.save(oldUser);

        return savedUser;
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

}