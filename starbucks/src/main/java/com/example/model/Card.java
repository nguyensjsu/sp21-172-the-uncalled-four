package com.example.model;

import javax.validation.constraints.NotBlank;

public class Card {

    int cardNumber;
    String firstName;
    String lastName;
    String exp_date;
    int ccv;

    public Card(int cardNumber, String firstName, String lastName, String exp_date, int ccv){
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.exp_date = exp_date;
        this.ccv = ccv; 
    }
    public int getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
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

    public String getExp_date() {
        return this.exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public int getCcv() {
        return this.ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

}
