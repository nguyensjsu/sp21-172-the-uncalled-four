package com.example.model;

import java.sql.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Register {
    
    @Id @GeneratedValue
    private  Long id;
    private int registerID;
    private int employeeID;
    private Date timestamp;

    public Register(int registerID, int employeeID, Date timestamp){
        this.registerID = registerID;
        this.employeeID = employeeID;
        this.timestamp = timestamp;
    }
    public int getRegisterID() {
        return this.registerID;
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
