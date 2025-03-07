package com.example.demo.service;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, Greeting updatedGreeting) {
        Optional<Greeting> existingGreeting = greetingRepository.findById(id);

        if(existingGreeting.isPresent()) {
            Greeting greeting = existingGreeting.get();
            greeting.setMessage(updatedGreeting.getMessage());
            return greetingRepository.save(greeting);
        } else {
            return null;
        }
    }

    public String deleteGreeting(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);

        if(greeting.isPresent()) {
            greetingRepository.deleteById(id);
            return "Greeting with ID: " + id + " deleted successfully!";
        } else {
            return "Greeting not found!";
        }
    }
}
