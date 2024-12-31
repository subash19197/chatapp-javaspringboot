/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp.model;

import java.util.Objects;

public class Message {
    private final String sender;
    private final String receiver;
    private final String content;

    
    public Message() {
        this.sender = "";
        this.receiver = "";
        this.content = "";
    }

  
    public Message(String sender, String receiver, String content) {
        if (sender == null || sender.isEmpty()) {
            throw new IllegalArgumentException("Sender must not be null or empty");
        }
        if (receiver == null || receiver.isEmpty()) {
            throw new IllegalArgumentException("Receiver must not be null or empty");
        }
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content must not be null or empty");
        }
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) &&
                Objects.equals(receiver, message.receiver) &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, content);
    }
}
