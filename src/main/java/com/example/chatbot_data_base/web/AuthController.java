package com.example.chatbot_data_base.web;

import com.example.chatbot_data_base.entities.User;
import com.example.chatbot_data_base.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if (userService.authenticateUser(user.getUsername(), user.getPassword())) {
            return "Login réussi !";
        } else {
            return "Échec de l'authentification. Veuillez vérifier vos informations d'identification.";
        }
    }
}