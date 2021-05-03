package com.example.model;

import java.sql.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private Date clockIN;
    private Date clockOUT;

    public Employee(String firstName, String lastName, Date clockIN, Date clockOUT){
        this.firstName = firstName;
        this.lastName = lastName;
        this.clockIN = clockIN;
        this.clockOUT = clockOUT;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getClockIN() {
        return this.clockIN;
    }

    public void setClockIN(Date clockIN) {
        this.clockIN = clockIN;
    }

    public Date getClockOUT() {
        return this.clockOUT;
    }

    public void setClockOUT(Date clockOUT) {
        this.clockOUT = clockOUT;
    }

}
