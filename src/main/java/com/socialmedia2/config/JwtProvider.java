package com.socialmedia2.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
    

    public static SecretKey key=Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        
        @SuppressWarnings("deprecation")
        String jwt=Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+86400000))
        .claim("email", auth.getName())
        .signWith(key)
        .compact();

        return jwt;
    }

    public static String getEmailFromToken(String jwt)
    {
        jwt=jwt.substring(7);

        @SuppressWarnings("deprecation")
        Claims claims=Jwts.parser()
        .setSigningKey(key).
        build().
        parseClaimsJws(jwt).
        getBody();

        String email=String.valueOf(claims.get("email"));

        return email;
    }



}