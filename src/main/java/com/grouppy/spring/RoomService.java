package com.grouppy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final UserRoomRepository userRoomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository, UserRoomRepository userRoomRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.userRoomRepository = userRoomRepository;
    }

    public String joinRoom(int userId, int roomId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (!userOptional.isPresent()) {
            return "User not found";
        }

        if (!roomOptional.isPresent()) {
            return "Room not found";
        }

        User user = userOptional.get();
        Room room = roomOptional.get();

        UserRoom userRoom = new UserRoom();
        userRoom.setUser(user);
        userRoom.setRoom(room);

        userRoomRepository.save(userRoom);

        return "User joined the room successfully";
    }
}
