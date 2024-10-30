package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.jpa.UsersRepository;
import com.techradar.polls.techradar_polls.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public User getUserById(Long id) {
        User user = usersRepository.findByUserId(id);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        return user;
    }
}
