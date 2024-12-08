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

import com.socialmedia2.models.Message;
import com.socialmedia2.models.User;
import com.socialmedia2.services.MessageService;
import com.socialmedia2.services.UserService;

@RestController
public class MessageController {
    

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/api/messages/chat/{chatId}")
    public ResponseEntity<Message> createChatHandler(@RequestBody Message message, @PathVariable Integer chatId,@RequestHeader("Authorization")String jwt) throws Exception{

        User user=userService.findUserByJwt(jwt);
        Message createdMessage=messageService.createMessage(user.getId(), chatId, message);
        return new ResponseEntity<Message>(createdMessage,HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public ResponseEntity<List<Message>> findChatMessagesHandler(@PathVariable Integer chatId){
    
        List<Message>messages=messageService.findChatMessages(chatId);
        return new ResponseEntity<List<Message>>(messages,HttpStatus.ACCEPTED);

    }

}