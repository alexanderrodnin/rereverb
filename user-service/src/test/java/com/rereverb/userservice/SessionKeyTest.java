package com.rereverb.userservice;

import com.rereverb.api.user.model.SessionKey;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SessionKeyTest {

    @Test
    void test() {
        SessionKey origin = SessionKey.builder()
                .userId(UUID.randomUUID())
                .salt("it's salt")
                .build();
        System.out.println(origin.toString());

        String b64 = origin.toBase64();
        System.out.println(b64);

        SessionKey restored = SessionKey.fromBase64(b64);
        System.out.println(restored);
    }

    @Test
    void testRandomString() {
        System.out.println(RandomString.make(300));
    }
}
