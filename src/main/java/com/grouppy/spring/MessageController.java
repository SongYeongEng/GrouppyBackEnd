package com.grouppy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Optional<Room> room = roomRepository.findById(message.getRoom().getRoomId());
        Optional<User> user = userRepository.findById(message.getUser().getUserId());

        if (room.isPresent() && user.isPresent()) {
            message.setRoom(room.get());
            message.setUser(user.get());
            Message savedMessage = messageRepository.save(message);
            return ResponseEntity.ok(savedMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message messageDetails) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            Message updatedMessage = message.get();
            updatedMessage.setMessageText(messageDetails.getMessageText());
            updatedMessage.setCreatedAt(messageDetails.getCreatedAt());
            messageRepository.save(updatedMessage);
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            messageRepository.delete(message.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}