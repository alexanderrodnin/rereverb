package com.rereverb.advertisement.rest.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialsDto {
    private String name;
    private String password;
}
