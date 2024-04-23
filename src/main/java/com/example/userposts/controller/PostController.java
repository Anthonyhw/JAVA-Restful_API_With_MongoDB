package com.example.userposts.controller;

import com.example.userposts.domain.Post;
import com.example.userposts.domain.User;
import com.example.userposts.dto.UserDTO;
import com.example.userposts.service.PostService;
import com.example.userposts.service.UserService;
import com.example.userposts.util.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        var post = service.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String title) {
        title = URL.decodeParam(title);
        var post = service.findByTitle(title);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Post>> findByDate(@RequestParam(value="date", defaultValue="") String date) {
        var convertedDate = URL.convertDate(date, new Date(0L));
        var post = service.findByDate(convertedDate);
        return ResponseEntity.ok(post);
    }
}
