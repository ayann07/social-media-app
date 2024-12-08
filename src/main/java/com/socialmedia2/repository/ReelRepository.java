package com.socialmedia2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia2.models.Reel;

public interface ReelRepository extends JpaRepository<Reel,Integer> {

    public List<Reel> findByUserId(Integer userId);
    
}