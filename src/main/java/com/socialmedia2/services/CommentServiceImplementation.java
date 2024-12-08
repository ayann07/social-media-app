package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Comment;
import com.socialmedia2.models.Post;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.CommentRepository;
import com.socialmedia2.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {

        User user = userService.findUserById(userId);
        Post post = postService.getPostById(postId);

        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setTimestamp(LocalDateTime.now());
        newComment.setUser(user);

        Comment saveComment = commentRepository.save(newComment);
        post.getComments().add(saveComment);

        postRepository.save(post);
        return saveComment;

    }

    @Override
    public Comment getCommentById(Integer commentId) throws Exception {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return comment.get();
        }
        throw new Exception("No comment found with id " + commentId);
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {

        Comment comment = getCommentById(commentId);
        User user = userService.findUserById(userId);
        comment.getLiked().add(user);
        return commentRepository.save(comment);

    }

}