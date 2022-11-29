package com.example.demo.dtos;

public class RoomStatus {
    private boolean isAvailable;
    private int roomNumber;
    private double price;

    public RoomStatus() {
        this.isAvailable = isAvailable;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
