/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.service;

import com.mycompany.chatapp.model.Message;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ChatService {

    private final Map<String, List<Message>> userMessages = new ConcurrentHashMap<>();

 
    public void sendMessage(String sender, String receiver, String content) {
        if (sender == null || sender.isEmpty()) {
            throw new IllegalArgumentException("Sender must not be null or empty");
        }
        if (receiver == null || receiver.isEmpty()) {
            throw new IllegalArgumentException("Receiver must not be null or empty");
        }
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content must not be null or empty");
        }

        Message message = new Message(sender, receiver, content);

       
        userMessages.computeIfAbsent(sender, k -> Collections.synchronizedList(new ArrayList<>())).add(message);

      
        userMessages.computeIfAbsent(receiver, k -> Collections.synchronizedList(new ArrayList<>())).add(message);
    }

  
    public List<Message> getMessageHistory(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }

        return userMessages.getOrDefault(username, Collections.emptyList());
    }
}
