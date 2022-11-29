package com.example.demo.model.rooms;

import com.example.demo.enums.Bed;
import com.example.demo.model.persons.Administrator;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Suite extends Room {

//    @ManyToOne
//    @JoinColumn(name="administrator_id")
//    private Administrator administrator;


//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;


    @ManyToMany(mappedBy = "suites")
    private Set<Reservation> reservations =new HashSet<>();

    public Suite() {}

    public Suite(boolean isAvailable, Bed typeOfBed, double pricePerNight, boolean breakfast, int roomNumber,int maxNumberOfResidents) {
        super(isAvailable, typeOfBed, pricePerNight, breakfast, roomNumber,maxNumberOfResidents);
    }
}

