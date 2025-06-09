package com.example.kata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/transform")
public interface TransformApi {

    /**
     * Transforme un nombre selon les règles FooBarQuix.
     *
     * @param number Nombre à transformer
     * @return Résultat de la transformation
     */
    @Operation(summary = "Transformer un nombre", description = "Transforme un nombre en appliquant les règles FOO, BAR, QUIX.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transformation réussie"),
            @ApiResponse(responseCode = "400", description = "Nombre invalide"),
            @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{number}")
    String transform(@PathVariable int number);
}
