package com.example.demo.dtos;

import java.time.LocalDate;

public class CostumerInfo {
    private String firstName;
    private String lastName;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public CostumerInfo() {
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }
}
