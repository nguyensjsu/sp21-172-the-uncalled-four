package com.example.starbucks.api;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Table;

import javax.persistence.Column;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
class StarbucksOrder {
    private @Id @GeneratedValue Long regid;
    private String drink;
    private String milk;
    private String size;
    private double total;
    private String status;
}