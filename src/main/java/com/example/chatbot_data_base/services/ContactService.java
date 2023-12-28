package com.example.chatbot_data_base.services;

import com.example.chatbot_data_base.dao.ContactRepository;
import com.example.chatbot_data_base.entities.Contact;
import com.example.chatbot_data_base.entities.QuestionReponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService {
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }
    public Contact saveContact(Contact contact) {
        return repository.save(contact);
    }
    public List<Contact> getListContact(){
        return  repository.findAll();
    }
    public void deleteContact(Long id){
        repository.deleteById(id);
    }

    public Contact updateContact(Long id , Contact contact) {
        Contact contact1 = repository.findById(id).orElseThrow(() -> new RuntimeException("Poster non trouvé avec l'ID : " + id));
        // si exist
        contact1.setQuestion(contact.getQuestion());
        contact1.setMail(contact.getMail());
        return repository.save(contact1);
    }
    public Contact getContact(Long id){
        return  repository.findById(id).get();
    }
}
