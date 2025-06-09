package com.example.kata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(summary = "Transformer un fichier .txt via batch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fichier transformé avec succès", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Erreur interne (ex. : batch ou fichier manquant)", content = @Content)
    })
    @PostMapping(value = "/transform-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Resource> transformFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("outputDirPath") String outputDirPath);

}
