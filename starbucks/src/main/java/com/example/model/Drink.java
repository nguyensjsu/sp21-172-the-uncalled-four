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
  @Column(nullable = false) private Type drink_type;

  public String getDrink_name() {
    return this.drink_name;
  }
  public void setDrink_name(String drink_name) {
    this.drink_name = drink_name;
  }
  public double getPrice(){
    return this.price;
  }
  public void setPrice(double price){
    this.price = price;
  }
  public Type getType(){
    return this.drink_type;
  }
  public void setType(Type type){
    this.drink_type = type;
  }

  
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

  

  public Drink (String drink_name, double price, Type drink_type){
    super();
    this.drink_name = drink_name;
    this.price = price;
    this.drink_type = drink_type;
  }


}
