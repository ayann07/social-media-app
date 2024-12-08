package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Reel;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.ReelRepository;

@Service
public class ReelServiceImplementation implements ReelService {

    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, Integer userId) throws Exception 
    {
     User user=userService.findUserById(userId);
     Reel newReel=new Reel();
     newReel.setTimestamp(LocalDateTime.now());
     newReel.setTitle(reel.getTitle());
     newReel.setUser(user);
     newReel.setVideo(reel.getVideo());
     return reelRepository.save(newReel);
    }

    @Override
    public List<Reel> getAllReelsByUserId(Integer userId) {
      return reelRepository.findByUserId(userId);
    }

    @Override
    public List<Reel> getAllReels() {
        return reelRepository.findAll();
    }
    
}