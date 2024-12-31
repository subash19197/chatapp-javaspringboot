/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mycompany.chatapp.model.Message;
import com.mycompany.chatapp.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServiceTest {

    private ChatService chatService;

    @BeforeEach
    public void setUp() {
        chatService = new ChatService();
    }

    @Test
    public void testSendMessage() {
        chatService.sendMessage("User1", "User2", "Hello, User2!");
        List<Message> messages = chatService.getMessageHistory("User1");
        assertEquals(1, messages.size());
        assertEquals("Hello, User2!", messages.get(0).getContent());
        assertEquals("User1", messages.get(0).getSender());
        assertEquals("User2", messages.get(0).getReceiver());
    }

    @Test
    public void testGetMessageHistory() {
        chatService.sendMessage("User1", "User2", "Hello, User2!");
        chatService.sendMessage("User2", "User1", "Hi, User1!");
        List<Message> messages = chatService.getMessageHistory("User1");
        assertEquals(2, messages.size());
        assertEquals("Hello, User2!", messages.get(0).getContent());
        assertEquals("Hi, User1!", messages.get(1).getContent());
    }

    @Test
    public void testEmptyMessageHistory() {
        List<Message> messages = chatService.getMessageHistory("User3");
        assertTrue(messages.isEmpty());
    }

    @Test
    public void testValidationForNullSender() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                chatService.sendMessage(null, "User2", "Hello, User2!")
        );
        assertEquals("Sender must not be null or empty", exception.getMessage());
    }

    @Test
    public void testValidationForEmptyReceiver() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                chatService.sendMessage("User1", "", "Hello, User2!")
        );
        assertEquals("Receiver must not be null or empty", exception.getMessage());
    }

    @Test
    public void testValidationForNullContent() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                chatService.sendMessage("User1", "User2", null)
        );
        assertEquals("Content must not be null or empty", exception.getMessage());
    }

    @Test
    public void testMessageOrder() {
        chatService.sendMessage("User1", "User2", "Message 1");
        chatService.sendMessage("User1", "User2", "Message 2");
        List<Message> messages = chatService.getMessageHistory("User1");
        assertEquals(2, messages.size());
        assertEquals("Message 1", messages.get(0).getContent());
        assertEquals("Message 2", messages.get(1).getContent());
    }
}
