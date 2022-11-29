package com.example.demo.dtos;

import java.time.LocalDate;

public class ReservationStatus {
    private double finalPrice;
    private boolean isComplete;
    private LocalDate checkIn;
    private LocalDate checkOut;


    public ReservationStatus() {
        this.finalPrice = finalPrice;


    }


    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
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
