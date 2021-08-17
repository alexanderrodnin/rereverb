package com.rereverb.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;
}
