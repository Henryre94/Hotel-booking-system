package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ReservationSummary {

    private int reservationId;
    private LocalDate reservationsDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String customerInfo;
    private Set singleRooms;
    private Set doubleRooms;
    private Set familyRooms;
    private Set suites;
    private double finalPrice;



}
