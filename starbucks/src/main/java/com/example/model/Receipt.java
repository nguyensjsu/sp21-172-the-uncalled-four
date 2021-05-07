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
public class Receipt {

    @Id @GeneratedValue
    private  Long id;
    private int receiptID;
    private Date timestamp;
    private double totalPrice;
    private int paymentID;

    public int getReceiptID() {
        return this.receiptID;
    }
    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPaymentID() {
        return this.paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    
}
