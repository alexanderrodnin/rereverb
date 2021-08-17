package com.rereverb.advertisement.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credentials {
    private String name;
    private String password;
}
