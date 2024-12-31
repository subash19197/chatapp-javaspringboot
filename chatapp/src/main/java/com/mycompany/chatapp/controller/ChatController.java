/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.controller;

import com.mycompany.chatapp.model.Message;
import com.mycompany.chatapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/chat")
@Validated  
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(
            @RequestParam(name = "sender") @NotEmpty(message = "Sender must not be empty") String sender,
            @RequestParam(name = "receiver") @NotEmpty(message = "Receiver must not be empty") String receiver,
            @RequestParam(name = "content") @NotEmpty(message = "Content must not be empty") String content) {

        logger.info("Sender: {}, Receiver: {}, Content: {}", sender, receiver, content);
        chatService.sendMessage(sender, receiver, content);
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/history")
    public ResponseEntity<List<Message>> getMessageHistory(
            @RequestParam(name = "username") @NotEmpty(message = "Username must not be empty") String username) {

        logger.debug("Fetching message history for username: {}", username);
        List<Message> history = chatService.getMessageHistory(username);
        return ResponseEntity.ok(history);
    }
}
