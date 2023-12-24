package com.example.chatbot_data_base.web;

import com.example.chatbot_data_base.services.QuestionReponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/chatbot")
public class ChatbotController {
    private final QuestionReponseService service;

    public ChatbotController(QuestionReponseService service) {
        this.service = service;
    }

    @GetMapping("/reponse")
    public ResponseEntity<String> obtenirReponse(@RequestParam String question) {
        String reponse = service.trouverReponse(question);
        return ResponseEntity.ok(reponse);
}
    @PostMapping("/ask")
    public ResponseEntity<String> poserQuestion(@RequestBody String question) {
        // Vous devrez adapter cette partie en fonction de votre service et de la logique de traitement
        String reponse = service.trouverReponse(question);
        return ResponseEntity.ok(reponse);
    }
}