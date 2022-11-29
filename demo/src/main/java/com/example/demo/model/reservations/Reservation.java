package com.example.demo.model.reservations;

import com.example.demo.model.persons.Administrator;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.rooms.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservationId;

    private String customerInfo;

    private int occupants;

    private int maxNumberOfResidents;

    private boolean isComplete;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private LocalDate reservationsDate;

    @Column(nullable = false)
    private double finalPrice;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "costumer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @ManyToMany(cascade=
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "SingleRoomsReserved",
            joinColumns = {@JoinColumn(name="reservation_id")},
            inverseJoinColumns = {@JoinColumn(name="room_id")}
    )
    private Set<SingleRoom> singleRooms = new HashSet<>();

    @ManyToMany(cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "DoubleRoomsReserved",
            joinColumns = {@JoinColumn(name="reservation_id")},
            inverseJoinColumns = {@JoinColumn(name="room_id")}
    )
    private Set<DoubleRoom> doubleRooms = new HashSet<>();

    @ManyToMany(cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "FamilyRoomsReserved",
            joinColumns = {@JoinColumn(name="reservation_id")},
            inverseJoinColumns = {@JoinColumn(name="room_id")}
    )
    private Set<FamilyRoom> familyRooms = new HashSet<>();

    @ManyToMany(cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "SuitesReserved",
            joinColumns = {@JoinColumn(name="reservation_id")},
            inverseJoinColumns = {@JoinColumn(name="room_id")}
    )
    private Set<Suite> suites = new HashSet<>();

    public Reservation (){}

    public Reservation(int reservationId,String customerInfo, int occupants,int maxNumberOfResidents,boolean isComplete, LocalDate checkIn, LocalDate checkOut,LocalDate reservationsDate, double finalPrice) {
        this.reservationId = reservationId;
        this.customerInfo = customerInfo;
        this.occupants = occupants;
        this.isComplete = isComplete;
        this.maxNumberOfResidents = maxNumberOfResidents;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.reservationsDate = reservationsDate;
        this.finalPrice = finalPrice;
    }

// Getter und Setter

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public int getMaxNumberOfResidents() {
        return maxNumberOfResidents;
    }

    public void setMaxNumberOfResidents(int maxNumberOfResidents) {
        this.maxNumberOfResidents = maxNumberOfResidents;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
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

    public LocalDate getReservationsDate() {
        return reservationsDate;
    }

    public void setReservationsDate(LocalDate reservationsDate) {
        this.reservationsDate = reservationsDate;
    }

    public Set<SingleRoom> getSingleRooms() {
        return singleRooms;
    }

    public void setSingleRooms(Set<SingleRoom> singleRooms) {
        this.singleRooms = singleRooms;
    }

    public void newSingleRoom(SingleRoom singleRoom){
        this.singleRooms.add(singleRoom);
    }

    public void removeSingleRoom(SingleRoom singleRoom){this.singleRooms.remove(singleRoom);}

    public Set<DoubleRoom> getDoubleRooms() {
        return doubleRooms;
    }

    public void setDoubleRooms(Set<DoubleRoom> doubleRooms) {
        this.doubleRooms = doubleRooms;
    }

    public void newDoubleRooms(DoubleRoom doubleRoom){
        this.doubleRooms.add(doubleRoom);
    }

    public void removeDoubleRoom(DoubleRoom doubleRoom){this.doubleRooms.remove(doubleRoom);}

    public Set<FamilyRoom> getFamilyRooms() {
        return familyRooms;
    }

    public void setFamilyRooms(Set<FamilyRoom> familyRooms) {
        this.familyRooms = familyRooms;
    }

    public void newFamilyRoom(FamilyRoom familyRoom){
        this.familyRooms.add(familyRoom);
    }

    public void removeFamilyRoom(FamilyRoom familyRoom){this.familyRooms.remove(familyRoom);}

    public Set<Suite> getSuites() {
        return suites;
    }

    public void setSuites(Set<Suite> suites) {
        this.suites = suites;
    }

    public void newSuite(Suite suite){
        this.suites.add(suite);
    }

    public void removeSuite(Suite suite){this.suites.remove(suite);}

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {this.customer = customer;}
}

