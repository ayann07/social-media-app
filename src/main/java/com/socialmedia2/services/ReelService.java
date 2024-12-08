package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Reel;

@Service
public interface ReelService {

    public Reel createReel(Reel reel,Integer userId) throws Exception;

    public List<Reel> getAllReelsByUserId(Integer userId);

    public List<Reel> getAllReels();
    
}