package com.socialmedia2.services;

import org.springframework.stereotype.Service;

import com.socialmedia2.models.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,Integer postId,Integer userId) throws Exception;

    public Comment getCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer commentId,Integer userId) throws Exception;

}