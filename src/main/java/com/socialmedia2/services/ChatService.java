package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Chat;
import com.socialmedia2.models.User;

public interface ChatService{

    public Chat createChat(User reqUser,User user2) throws Exception;
    
    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}
