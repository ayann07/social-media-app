package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Chat;
import com.socialmedia2.models.Message;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.ChatRepository;
import com.socialmedia2.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(Integer userId, Integer chatId, Message message) throws Exception {

        Chat chat=chatService.findChatById(chatId);
        User user=userService.findUserById(userId);

        Message newMessage=new Message();
        newMessage.setChat(chat);
        newMessage.setContent(message.getContent());
        newMessage.setImage(message.getImage());
        newMessage.setUser(user);
        newMessage.setTimestamp(LocalDateTime.now());

        Message savedMessage=messageRepository.save(newMessage);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat); 

        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) {
        return messageRepository.findByChatId(chatId);
    }
    
}