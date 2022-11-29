package com.example.demo.model.rooms;

import com.example.demo.enums.Bed;


import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;
    @Column(nullable = false)
    private boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private Bed typeOfBed;
    @Column(nullable = false)
    private double pricePerNight;
    @Column(nullable = false)
    private boolean breakfast;
    @Column(nullable = false)
    private int roomNumber;

    private int maxNumberOfResidents;


    public Room (){}

    public Room(boolean isAvailable, Bed typeOfBed,  double pricePerNight, boolean breakfast, int roomNumber,int maxNumberOfResidents) {
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.breakfast = breakfast;
        this.roomNumber = roomNumber;
        this.typeOfBed = typeOfBed;
        this.maxNumberOfResidents = maxNumberOfResidents;

    }

// Getter und Setter

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return pricePerNight;
    }

    public void setPrice(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Bed getTypeOfBed() {
        return typeOfBed;
    }

    public void setTypeOfBed(Bed typeOfBed) {
        this.typeOfBed = typeOfBed;
    }

    public int getMaxNumberOfResidents() {
        return maxNumberOfResidents;
    }

    public void setMaxNumberOfResidents(int maxNumberOfResidents) {
        this.maxNumberOfResidents = maxNumberOfResidents;
    }

}

