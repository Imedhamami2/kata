package com.example.kata.controller.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mockMvc.perform(get("/api/transform/" + input))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void testTransformEndpointNegativeNumber() throws Exception {
        mockMvc.perform(get("/api/transform/-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTransformEndpointAboveRange() throws Exception {
        mockMvc.perform(get("/api/transform/101"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTransformFileEndpoint() throws Exception {
        // Préparer un fichier texte simulé
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test-input.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "3\n5\n15".getBytes()
        );

        // Appel du endpoint avec MultipartFile + paramètre outputDirPath
        mockMvc.perform(multipart("/api/transform/transform-file")
                        .file(file)
                        .param("outputDirPath", "target/test-output"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=output.txt"))
                .andExpect(content().contentType(MediaType.TEXT_PLAIN));
    }
}
