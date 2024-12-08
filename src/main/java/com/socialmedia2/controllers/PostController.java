package com.socialmedia2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia2.models.Post;
import com.socialmedia2.models.User;
import com.socialmedia2.response.ApiResponse;
import com.socialmedia2.services.PostService;
import com.socialmedia2.services.UserService;

@RestController
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post, user.getId());
        return new ResponseEntity<Post>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId, user.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPosts() {

        List<Post> ls = postService.findAllPosts();
        return new ResponseEntity<List<Post>>(ls, HttpStatus.OK);
    }

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> getUserPostsHandler(@PathVariable Integer userId) {

        List<Post> ls = postService.getPostsByUserId(userId);
        return new ResponseEntity<List<Post>>(ls, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> getPostByIdHandler(@PathVariable Integer postId) throws Exception {

        Post post = postService.getPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception{

        User user=userService.findUserByJwt(jwt);
        Post post=postService.likePost(postId, user.getId());
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

}