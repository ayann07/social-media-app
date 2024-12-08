package com.socialmedia2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia2.models.Comment;
import com.socialmedia2.models.User;
import com.socialmedia2.services.CommentService;
import com.socialmedia2.services.UserService;

@RestController
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comments/post/{postId}")
    public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment,
            @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Comment createComment = commentService.createComment(comment, postId, user.getId());
        return new ResponseEntity<Comment>(createComment, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByIdHandler(@PathVariable Integer commentId) throws Exception{
        Comment comment=commentService.getCommentById(commentId);
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }

    @PutMapping("/api/comments/like/{commentId}")
    public ResponseEntity<Comment> likeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user=userService.findUserByJwt(jwt);
        Comment comment=commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }

}