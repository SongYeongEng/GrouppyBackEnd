package com.grouppy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userrooms")
public class UserRoomController {
    private final UserRoomRepository userRoomRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public UserRoomController(UserRoomRepository userRoomRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.userRoomRepository = userRoomRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<UserRoom> getUserRooms() {
        return userRoomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoom> getUserRoomById(@PathVariable int id) {
        Optional<UserRoom> userRoom = userRoomRepository.findById(id);
        if (userRoom.isPresent()) {
            return ResponseEntity.ok(userRoom.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserRoom> createUserRoom(@RequestBody UserRoom userRoom) {
        Optional<User> user = userRepository.findById(userRoom.getUser().getUserId());
        Optional<Room> room = roomRepository.findById(userRoom.getRoom().getRoomId());

        if (user.isPresent() && room.isPresent()) {
            userRoom.setUser(user.get());
            userRoom.setRoom(room.get());
            userRoom.setJoinedAt(new Timestamp(System.currentTimeMillis()));
            UserRoom savedUserRoom = userRoomRepository.save(userRoom);
            return ResponseEntity.ok(savedUserRoom);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoom> updateUserRoom(@PathVariable int id, @RequestBody UserRoom userRoomDetails) {
        Optional<UserRoom> userRoom = userRoomRepository.findById(id);
        if (userRoom.isPresent()) {
            UserRoom updatedUserRoom = userRoom.get();
            updatedUserRoom.setJoinedAt(userRoomDetails.getJoinedAt());
            userRoomRepository.save(updatedUserRoom);
            return ResponseEntity.ok(updatedUserRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRoom(@PathVariable int id) {
        Optional<UserRoom> userRoom = userRoomRepository.findById(id);
        if (userRoom.isPresent()) {
            userRoomRepository.delete(userRoom.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
