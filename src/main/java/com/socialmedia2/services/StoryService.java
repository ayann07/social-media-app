package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Story;
import com.socialmedia2.models.User;


public interface StoryService {

    public Story createStory(Story story,User user);

    public List<Story> findAllStoryByUserId(Integer userId);

    public List<Story> findAllStories();
    
}