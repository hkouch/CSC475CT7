package com.example.ct7o1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConversionUtilsTest {

    @Test
    public void testConvertToKm() {
        assertEquals(1.0, MainActivity.convertToKm(1.0, "km"), 0.0001);
        assertEquals(0.001, MainActivity.convertToKm(1.0, "m"), 0.0001);
        assertEquals(0.00001, MainActivity.convertToKm(1.0, "cm"), 0.0001);
        assertEquals(0.000001, MainActivity.convertToKm(1.0, "mm"), 0.0001);
        assertEquals(0.000000001, MainActivity.convertToKm(1.0, "microm"), 0.0001);
        assertEquals(1.0E-12, MainActivity.convertToKm(1.0, "nm"), 0.0001);
        assertEquals(1.60934, MainActivity.convertToKm(1.0, "mile"), 0.0001);
        assertEquals(0.0009144, MainActivity.convertToKm(1.0, "yard"), 0.0001);
        assertEquals(0.0003048, MainActivity.convertToKm(1.0, "foot"), 0.0001);
        assertEquals(0.0000254, MainActivity.convertToKm(1.0, "inch"), 0.0001);
    }
}
