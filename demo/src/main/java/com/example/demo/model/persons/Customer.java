package com.example.demo.model.persons;

import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Customers")
public class Customer extends Person {

    @Column(nullable = false)
    private int numberOfCostumers;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private Set<Reservation> reservationSet = new HashSet<>();

    public Customer(){}

    public Customer(String fistName, String lastName, String address, String phoneNumber, String eMail, int numberOfCostumers, LocalDate checkIn, LocalDate checkOut) {
        super(fistName,lastName, address, phoneNumber,eMail);
        this.numberOfCostumers = numberOfCostumers;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getNumberOfCostumers() {
        return numberOfCostumers;
    }

    public void setNumberOfCostumers(int numberOfCostumers) {
        this.numberOfCostumers = numberOfCostumers;
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
