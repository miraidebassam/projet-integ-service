package com.esmt.userserviceclient.models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String productName;
    private int quantity;
    private double price;
    private String address;
}
