package com.rereverb.order.model;

import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private UUID id;
    private String email;
    private String name;
    private String password;
}
