package com.rereverb.order.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private UUID id;
    private String email;
    private String name;
    private String password;
}
