package org.example.controller;

import org.example.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatGptController {

    @Autowired
    private ChatGptService chatGptService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        try {
            return chatGptService.askChatGpt(userMessage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
