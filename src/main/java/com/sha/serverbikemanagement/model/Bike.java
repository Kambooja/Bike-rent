package com.sha.serverbikemanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;
}
