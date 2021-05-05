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
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table
@Data
@RequiredArgsConstructor
class PaymentsCommand {
    
    @Id @GeneratedValue
    private  Long id;
    transient private String action ;

    private String cardname;
    private String cardnum ;
    private String cardexpmon;
    private String cardexpyear;
    private String cardcvv;
 
    public String cardname(){return cardname;}
    public String cardnum(){return cardnum ;}
    public String cardexpmon(){return cardexpmon;}
    public String cardexpyear(){return cardexpyear;}
    public String cardcvv(){return cardcvv ;}
<<<<<<< HEAD

    
    private String orderNumber ;
    private String transactionAmount;
    private String transactionCurrency;
    private String authId;
    private String authStatus;
    private String captureId ;
    private String captureStatus;
}
=======
}*/
>>>>>>> 13286978b5fcc5e491420ff086c5190fbe184a7a
