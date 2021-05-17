package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Order {

  @Column(nullable = false) private String orderName;
  @Column(nullable = false) private List<String> ingredients;
  @Column(nullable = false) private String price;

  public Order(String orderName, List<String> ingredients) {
      this.orderName = orderName;
      this.ingredients = ingredients;
  }

public Order() {
}

}
