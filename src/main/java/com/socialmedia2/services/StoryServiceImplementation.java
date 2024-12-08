package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Story;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story createStory(Story story, User user) {

        Story newStory=new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setTimestamp(LocalDateTime.now());
        newStory.setUser(user);

        return storyRepository.save(newStory);
    }

    @Override
    public List<Story> findAllStoryByUserId(Integer userId) {
        return storyRepository.findByUserId(userId);
    }

    @Override
    public List<Story> findAllStories() {
        return storyRepository.findAll();
    }
    
}