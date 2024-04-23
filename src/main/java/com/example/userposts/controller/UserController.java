package com.example.userposts.controller;

import com.example.userposts.domain.Post;
import com.example.userposts.domain.User;
import com.example.userposts.dto.UserDTO;
import com.example.userposts.service.UserService;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService service;
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        var users = service.findAll();
        List<UserDTO> response = users.stream().map(x  -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        var user = service.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        var user = service.findById(id);
        return ResponseEntity.ok(user.getPosts());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO user) {
        User u = UserDTO.fromDTO(user);
        u = service.insert(u);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO user, @PathVariable String id) {
        User u = UserDTO.fromDTO(user);
        u.setId(id);
        u = service.update(u);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
