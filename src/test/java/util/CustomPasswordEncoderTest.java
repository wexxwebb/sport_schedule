package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomPasswordEncoderTest {

    @Test
    public void encode() {
    }

    @Test
    public void matches() {
        CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
        String pass = "john";
        System.out.println(pass);
        System.out.println(passwordEncoder.encode(pass));
        System.out.println(passwordEncoder.encode(pass));
    }
}