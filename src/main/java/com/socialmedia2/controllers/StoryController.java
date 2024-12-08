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

import com.socialmedia2.models.Story;
import com.socialmedia2.models.User;
import com.socialmedia2.services.StoryService;
import com.socialmedia2.services.UserService;

@RestController
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    @PostMapping("/api/story")
    public ResponseEntity<Story> createStoryHandler(@RequestBody Story story,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, user);
        return new ResponseEntity<Story>(createdStory, HttpStatus.OK);
    }

    @GetMapping("/api/story/user/{userId}")
    public ResponseEntity<List<Story>> getAllStoryByUserIdHandler(@PathVariable Integer userId) {
        List<Story> stories = storyService.findAllStoryByUserId(userId);
        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }

    @GetMapping("/api/story")
    public ResponseEntity<List<Story>> getAllStoriesHandler() {
        List<Story> stories = storyService.findAllStories();
        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
    }
}