package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Chat;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) throws Exception {

        Chat isExist=chatRepository.findChatByUsersId(reqUser,user2);
        if(isExist!=null)
        {
            return isExist;
        }
        Chat newChat=new Chat();
        newChat.getUsers().add(reqUser);
        newChat.getUsers().add(user2);
        newChat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {

        Optional<Chat> chat=chatRepository.findById(chatId);
        if(chat.isPresent())
        {
            return chat.get();
        }
        throw new Exception("No chat found with this id in the database!!");
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
    
}