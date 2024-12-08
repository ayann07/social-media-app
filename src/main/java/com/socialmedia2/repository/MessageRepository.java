package com.socialmedia2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia2.models.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    
    public List<Message> findByChatId(Integer chatId);

}