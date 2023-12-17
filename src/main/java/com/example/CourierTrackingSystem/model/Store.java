package com.example.CourierTrackingSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stores")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Store {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private double lat;

    @Column(name = "long")
    private double lng;

}
