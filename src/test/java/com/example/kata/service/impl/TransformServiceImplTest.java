package com.example.kata.service.impl;

import com.example.kata.service.TransformService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransformServiceImplTest {
    private final TransformService service = new TransformServiceImpl();

    @ParameterizedTest
    @MethodSource("com.example.kata.TestDataProvider#provideFooBarQuixData")
    public void testTransform(int input, String expected) {
        assertEquals(expected, service.transform(input));
    }
    @Test
    public void testTransformThrowsExceptionForNegative() {
        assertThrows(IllegalArgumentException.class, () -> service.transform(-1));
    }

    @Test
    public void testTransformThrowsExceptionAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> service.transform(101));
    }
}
