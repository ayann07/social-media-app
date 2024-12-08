package com.socialmedia2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia2.models.Post;
import com.socialmedia2.models.User;
import com.socialmedia2.repository.PostRepository;


@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setImage(post.getImage());
        newPost.setUser(user);
        newPost.setVideo(post.getVideo());

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Optional<Post> post = postRepository.findById(userId);
        Post dp;
        if (post.isPresent()) {
            dp = post.get();

            if (dp.getUser().getId() != userId) {
                throw new Exception("You cannot delete someone else posts !!!");
            }
            postRepository.delete(dp);
            return "Post deleted successfully";
        } else {
            throw new Exception("Unable to delete post");
        }

    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post getPostById(Integer postId) throws Exception {

        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new Exception("No post found with given id");
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {

        Post post=getPostById(postId);
        User user=userService.findUserById(userId);

        post.getLikes().add(user);
        return postRepository.save(post);
        
    }

}