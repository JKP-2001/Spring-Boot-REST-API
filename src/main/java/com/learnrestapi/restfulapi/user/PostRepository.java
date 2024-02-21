package com.learnrestapi.restfulapi.user;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>{

    // public ArrayList<Post> findByUser(User user);
}
