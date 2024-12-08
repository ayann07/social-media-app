package com.socialmedia2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia2.models.Reel;
import com.socialmedia2.models.User;
import com.socialmedia2.services.ReelService;
import com.socialmedia2.services.UserService;

@RestController
public class ReelController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReelService reelService;
    
    @PostMapping("/api/reels")
    public ResponseEntity<Reel> createReelHandler(@RequestBody Reel reel,@RequestHeader("Authorization") String jwt) throws Exception {

        User user=userService.findUserByJwt(jwt);
        Reel createdReel=reelService.createReel(reel,user.getId());
        return new ResponseEntity<Reel>(createdReel,HttpStatus.OK);
    }

    @GetMapping("/api/reels/user/{userId}")
    public ResponseEntity<List<Reel>> getAllReelsByUserIdHandler(@PathVariable Integer userId){
       List<Reel> reels=reelService.getAllReelsByUserId(userId);
       return new ResponseEntity<List<Reel>>(reels,HttpStatus.OK);
    }

    @GetMapping("/api/reels")
    public ResponseEntity<List<Reel>> getAllReelsHandler(){
       List<Reel> reels=reelService.getAllReels();
       return new ResponseEntity<List<Reel>>(reels,HttpStatus.OK);
    }

}