package com.example.chatbot_data_base.services;

import com.example.chatbot_data_base.dao.QuestionReponseRepository;
import com.example.chatbot_data_base.entities.QuestionReponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionReponseService {
    private final QuestionReponseRepository repository;

    public QuestionReponseService(QuestionReponseRepository repository) {
        this.repository = repository;
    }

    public String trouverReponse(String question) {
        List<QuestionReponse> allQuestionsReponses = repository.findAll();

        String reponse = "Désolé, je n'ai pas de réponse pour cette question.";
        int minDistance = Integer.MAX_VALUE;
        String questionLaPlusProche = "";

        for (QuestionReponse qr : allQuestionsReponses) {
            int distance = calculerDistanceLevenshtein(qr.getQuestion(), question);
            if (distance < minDistance) {
                minDistance = distance;
                questionLaPlusProche = qr.getQuestion();
                reponse = qr.getReponse();
            }
        }
        // Définir un seuil de précision (par exemple, 70%)
        int longueurMax = Math.max(question.length(), questionLaPlusProche.length());
        double precision = 1.0 - (double) minDistance / longueurMax;

        // Vérifier si la précision est supérieure ou égale au seuil
        if (precision >= 0.7) {
            return reponse;
        } else {
            return "Vous avez posé : '" + question + "'. Peut-être vouliez-vous dire : '" + questionLaPlusProche + "' ? Réponse : " + reponse;
        }
    }

    private int calculerDistanceLevenshtein(String s1, String s2) {
        int[][] distance = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }

        return distance[s1.length()][s2.length()];
    }
}
