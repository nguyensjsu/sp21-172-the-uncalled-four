package com.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@RequiredArgsConstructor
public class Drink {

  @Id @GeneratedValue
  private  Long id;
  @Column(nullable = false) private String drink_name;
  @Column(nullable = false) private double price;
  @Column(nullable = false) private String drink_type;
  
  public static enum Type {
    BREWED_COFEE, 
    FLAT_WHITE, 
    MACCHIATO, 
    MOCHA, 
    FRAPUCCINO, 
    CAPUCCINO,
    EXPRESSO_SHOT,
    LATTE,
  }

  public Drink (String drink_name, double price, String drink_type){
    super();
    this.drink_name = drink_name;
    this.price = price;
    this.drink_type = drink_type;
  }


}
