package com.example.userposts.repository;

import com.example.userposts.domain.Post;
import com.example.userposts.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
     List<Post> findByTitleContainingIgnoreCase(String text);
     @Query("{ date: { $eq: ?0 } }")
     List<Post> findDate(Date date);
}
