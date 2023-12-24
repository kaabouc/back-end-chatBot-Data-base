package com.example.chatbot_data_base.dao;

import com.example.chatbot_data_base.entities.QuestionReponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionReponseRepository extends JpaRepository<QuestionReponse, Long> {
    QuestionReponse findByQuestion(String question);
}