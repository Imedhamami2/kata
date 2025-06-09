package com.example.kata.controller.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test d'intégration pour l'API REST de transformation.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TransformControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("com.example.kata.TestDataProvider#provideFooBarQuixData")
    public void testTransformEndpoint(int input, String expected) throws Exception {
        mockMvc.perform(get("/transform/" + input))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void testTransformEndpointNegativeNumber() throws Exception {
        mockMvc.perform(get("/transform/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTransformEndpointAboveRange() throws Exception {
        mockMvc.perform(get("/transform/101"))
                .andExpect(status().isBadRequest());
    }
}
