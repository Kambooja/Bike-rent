package com.sha.serverbikemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //user rent many bikes
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bike_id", referencedColumnName = "id")
    private Bike bike;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "rentDate")
    @JoinColumn(name = "rent_date", referencedColumnName = "id")
    private LocalDateTime rentDate;


    @Column(name = "return_date")
    private LocalDateTime returnDate;


}
