package com.example.userposts.service;

import com.example.userposts.domain.Post;
import com.example.userposts.domain.User;
import com.example.userposts.exception.ObjectNotFoundException;
import com.example.userposts.repository.PostRepository;
import com.example.userposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;
    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> findByDate(Date date) {
        return repository.findDate(date);
    }
}
