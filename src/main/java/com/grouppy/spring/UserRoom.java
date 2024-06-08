package com.grouppy.spring;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "UserRooms")
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoomId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private Timestamp joinedAt;

    // Getters and Setters
    public int getUserRoomId() {
        return userRoomId;
    }

    public void setUserRoomId(int userRoomId) {
        this.userRoomId = userRoomId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Timestamp getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Timestamp joinedAt) {
        this.joinedAt = joinedAt;
    }
}
