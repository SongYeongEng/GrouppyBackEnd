package com.grouppy.spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int sender_id;
    private String content;


    // Constructors, getters, and setters
    public Message() {}

    public Message(String content, int sender_id) {
        this.content = content;
        this.sender_id = sender_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSender() {
        return sender_id;
    }

    public void setSender(int sender) {
        this.sender_id = sender_id;
    }
}
