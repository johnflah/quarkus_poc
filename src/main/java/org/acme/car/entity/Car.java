package org.acme.car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "car")
@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String make;

    @Column(length = 100, nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(length = 50, nullable = false)
    private String colour;

    @Transient
    private ZonedDateTime washed;
    @Transient
    private ZonedDateTime serviced;


}
