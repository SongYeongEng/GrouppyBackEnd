package com.grouppy.spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public User(){}

    // Constructors, getters, and setters
    public User(String name){
        this.name = name;
    }

    public int getID(){
        return id;
    }


    public void setID(int id){
        this.id = id;
    }

    public String getUsername(){
        return name;
    }

    public void setUsername(String username){
        this.name = name;
    }

}
