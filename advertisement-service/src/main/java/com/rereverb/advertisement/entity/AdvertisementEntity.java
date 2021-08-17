package com.rereverb.advertisement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "advertisement")
@NoArgsConstructor
@Getter
@Setter
public class AdvertisementEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String header;

    @Column
    private String description;

    @Column
    private double price;
}
