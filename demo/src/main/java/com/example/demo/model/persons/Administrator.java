package com.example.demo.model.persons;

import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Administrator")
public class Administrator extends Person {

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private double salaryBeforeTaxes;

    @Column(nullable = false)
    private boolean bonus;

    @Column(nullable = false)
    private boolean admin;

    public Administrator(){}

    public Administrator(String name,String lastName, String address, String phoneNumber,String eMail, String position, double salaryBeforeTaxes, boolean bonus, boolean admin) {
        super(name,lastName, address, phoneNumber,eMail);
        this.position = position;
        this.salaryBeforeTaxes = salaryBeforeTaxes;
        this.bonus = bonus;
        this.admin = admin;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalaryBeforeTaxes() {
        return salaryBeforeTaxes;
    }

    public void setSalaryBeforeTaxes(double salaryBeforeTaxes) {
        this.salaryBeforeTaxes = salaryBeforeTaxes;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}



