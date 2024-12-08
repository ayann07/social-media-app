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

import com.socialmedia2.models.Chat;
import com.socialmedia2.models.User;
import com.socialmedia2.request.CreateChatRequest;
import com.socialmedia2.services.ChatService;
import com.socialmedia2.services.UserService;

@RestController
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;
    
    @PostMapping("/api/chats")
    public ResponseEntity<Chat> createChatHandler(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception{

        User reqUser=userService.findUserByJwt(jwt);
        User user2=userService.findUserById(req.getUserId());
        Chat chat=chatService.createChat(reqUser, user2);
        return new ResponseEntity<Chat>(chat,HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/chats/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId) throws Exception{

        Chat chat=chatService.findChatById(chatId);
        return new ResponseEntity<Chat>(chat,HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/chats/")
    public ResponseEntity<List<Chat>> findUsersChatHandler(@RequestHeader("Authorization") String jwt) throws Exception{

        User user=userService.findUserByJwt(jwt);
        List<Chat> chat=chatService.findUsersChat(user.getId());
        return new ResponseEntity<List<Chat>>(chat,HttpStatus.ACCEPTED);
    }
}