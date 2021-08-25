package com.rereverb.api.user.model;

import lombok.*;

import java.util.Base64;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionKey {
    private UUID userId;
    private String salt;

    public String toBase64() {
        return Base64.getEncoder().encodeToString((userId.toString() + ";" + salt).getBytes());
    }

    public static SessionKey fromBase64(String base64Key) {
        String decoded = new String(Base64.getDecoder().decode(base64Key));
        String[] split = decoded.split(";");
        return SessionKey.builder()
                .userId(UUID.fromString(split[0]))
                .salt(split[1])
                .build();
    }
}
