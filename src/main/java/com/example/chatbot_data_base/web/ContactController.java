package com.example.chatbot_data_base.web;

import com.example.chatbot_data_base.dao.ContactRepository;
import com.example.chatbot_data_base.entities.Contact;
import com.example.chatbot_data_base.entities.QuestionReponse;
import com.example.chatbot_data_base.services.ContactService;
import com.example.chatbot_data_base.services.QuestionReponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@Controller
public class ContactController {
    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service ;
    }

    @PostMapping("/contact/create")
    public Contact saveContact(@RequestBody Contact contact){
        return    service.saveContact(contact);
    }

    @PutMapping("/contact/update/{id}")
    public  Contact updateContact(@PathVariable Long id , @RequestBody Contact contact){
        return  service.updateContact(id,contact);
    }

    @DeleteMapping("/contact/{id}")
    public  void deleteContact(@PathVariable Long id ){
        service.deleteContact(id);
    }

    @GetMapping("/contact/all")
    public List<Contact> getAllContact(){
        return   service.getListContact();
    }
    @GetMapping("/contact/get/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return service.getContact(id);
    }

}
