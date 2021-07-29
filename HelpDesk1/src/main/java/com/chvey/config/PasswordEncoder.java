package com.chvey.config;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

@Component
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return new BASE64Encoder().encode(((String) rawPassword).getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(((String) rawPassword).getBytes()).equals(encodedPassword);
    }
}
