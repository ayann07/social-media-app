package com.socialmedia2.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia2.config.JwtProvider;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.UserRepository;
import com.socialmedia2.request.LoginRequest;
import com.socialmedia2.response.AuthResponse;
import com.socialmedia2.services.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("User already exists " + isExist);
        }

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setGender(user.getGender());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Registration successful");
        return res;
    }

    public AuthResponse signin(@RequestBody LoginRequest req) {
        Authentication authentication=authenticate(req.getEmail(), req.getPassword());
        String token=JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,"logged in successfully");
        return res;
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}