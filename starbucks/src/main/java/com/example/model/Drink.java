package com.example.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Drink {
  
  private final String id;
  private final String name;
  private final Type type;
  
  public static enum Type {
    HOT_COFFEE, HOT_TEA, COLD_COFFEE, ICED_TEA, FRAPUCCINO,
  }
}
