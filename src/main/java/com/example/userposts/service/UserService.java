package com.example.userposts.service;

import com.example.userposts.domain.User;
import com.example.userposts.exception.ObjectNotFoundException;
import com.example.userposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public User delete(String id) {
        User user = findById(id);
        repository.deleteById(id);
        return user;
    }

    public User update(User user) {
        User dbUser = findById(user.getId());
        updateData(dbUser, user);
        return repository.save(dbUser);
    }

    private void updateData(User oldUser, User newUser) {
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
    }
}
