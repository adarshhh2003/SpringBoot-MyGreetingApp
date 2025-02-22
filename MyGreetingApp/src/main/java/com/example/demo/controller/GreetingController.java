package com.example.demo.controller;

import com.example.demo.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

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
}
