package com.example.demo.service;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public String getGreetingMessage(String firstName, String lastName) {
        String message;
        if(firstName != null && lastName != null) {
            message = "Hello " + firstName + " " + lastName + " !";
        } else if(firstName != null) {
            message =  "Hello " + firstName + " !";
        } else if(lastName != null) {
            message = "Hello " + lastName + " !";
        } else {
            message = "Hello World!";
        }
        saveGreeting(message);
        return message;
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElse(null);
    }
}
