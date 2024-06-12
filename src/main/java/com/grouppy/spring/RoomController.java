package com.grouppy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomRepository roomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        room.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        room.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody Room roomDetails) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            Room updatedRoom = room.get();
            updatedRoom.setRoomName(roomDetails.getRoomName());
            updatedRoom.setMaxPerson(roomDetails.getMaxPerson());
            updatedRoom.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            roomRepository.save(updatedRoom);
            return ResponseEntity.ok(updatedRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            roomRepository.delete(room.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<String> joinRoom(@PathVariable int roomId, @RequestParam int userId) {
        String result = roomService.joinRoom(userId, roomId);
        if (result.equals("User joined the room successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
