package com.example.model;


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
public class Drink {

  @Id @GeneratedValue
  private  Long id;
  private String drink_name;
  private double price;
  private Type drink_type;
  
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
}
