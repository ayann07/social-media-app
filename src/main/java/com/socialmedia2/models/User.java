package com.socialmedia2.models;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName,lastName,email,password,gender;

    private List<Integer>followers=new ArrayList<>();
    private List<Integer>followings=new ArrayList<>();

    @ManyToMany
    private List<Post>savePosts=new ArrayList<>();

}