package com.example.demo.model.persons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends Person {

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private double salaryBeforeTaxes;

    @Column(nullable = false)
    private boolean bonus;

    @Column(nullable = false)
    private boolean admin;

//    @ManyToOne
//    @JoinColumn(name="admin_id")
//    private Administrator administrator;

    public Employee() {}

    public Employee(String fistName,String lastName, String address, String phoneNumber,String  eMail, String position, double salaryBeforeTaxes, boolean bonus, boolean admin) {
        super(fistName,lastName, address, phoneNumber, eMail);
        this.position = position;
        this.salaryBeforeTaxes = salaryBeforeTaxes;
        this.bonus = bonus;
        this.admin = admin;
    }
    // Getter & Setter


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        if (isBonus()==true) {
            salaryBeforeTaxes = salaryBeforeTaxes * 1.01;
        }
        return salaryBeforeTaxes;
    }

    public void setSalary(double salary) {
        this.salaryBeforeTaxes = salary;
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


