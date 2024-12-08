package com.socialmedia2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Post;

public interface PostService {

    Post createNewPost(Post post, Integer userId) throws Exception;
 
    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findAllPosts();

    List<Post> getPostsByUserId(Integer userId);

    Post getPostById(Integer postId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;

}