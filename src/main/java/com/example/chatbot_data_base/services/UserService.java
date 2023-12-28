package com.example.chatbot_data_base.services;

import com.example.chatbot_data_base.dao.UserRepository;
import com.example.chatbot_data_base.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && ((User) user).getPassword().equals(password);
    }
}