package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.model.Message;
import com.example.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingService;

    @GetMapping
    public Message getGreeting() {
        return new Message("Hello, this is a GET request!");
    }

    @PostMapping
    public Message postGreeting(@RequestBody Message message) {
        return new Message("Received POST request: " + message.getContent());
    }

    @PutMapping
    public Message putGreeting(@RequestBody Message message) {
        return new Message("Updated with PUT request: " + message.getContent());
    }

    @DeleteMapping
    public Message deleteGreeting() {
        return new Message("Resource deleted with DELETE request!");
    }

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/personalized")
    public Message getGreetingService(@RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName) {
        return new Message(greetingService.getGreetingMessage(firstName, lastName));
    }

    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting.getMessage());
    }

    @GetMapping("/{id}")
    public Greeting getGreeting(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }
}
