package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Message;

public interface MessageService {
    
    public Message createMessage(Integer userId,Integer chatId,Message message) throws Exception;

    public List<Message> findChatMessages(Integer chatId);

}