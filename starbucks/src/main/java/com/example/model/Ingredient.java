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
public class Ingredient {
    @Id @GeneratedValue
    private  Long id;
    
}
