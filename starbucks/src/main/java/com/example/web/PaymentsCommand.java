/*package com.example.web;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table
@Data
@RequiredArgsConstructor
class PaymentsCommand {
    
    //@Id @GeneratedValue
    private  Long id;
    transient private String action ;
    private String firstname ;
    private String lastname ;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone ;
    private String cardnum ;
    private String cardexpmon;
    private String cardexpyear;
    private String cardcvv;
 

    public String firstname(){return firstname ;}
    public String lastname(){return lastname;}
    public String address(){return address;}
    public String city(){return city ; }
    public String state(){return state;}
    public String zip(){return zip;}
    public String phone(){return phone;}
    public String cardnum(){return cardnum ;}
    public String cardexpmon(){return cardexpmon;}
    public String cardexpyear(){return cardexpyear;}
    public String cardcvv(){return cardcvv ;}
}*/