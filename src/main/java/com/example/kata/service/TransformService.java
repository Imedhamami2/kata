package com.example.kata.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface définissant le contrat du service de transformation.
 */
public interface TransformService {

    /**
     * Transforme un entier selon les règles du kata Foo Bar Quix.
     *
     * @param number le nombre à transformer
     * @return la chaîne résultante après transformation
     */
    String transform(int number);

    /**
     * Transforme un fichier texte contenant une liste de nombres en appliquant la logique FooBarQuix.
     *
     * Cette méthode effectue les opérations suivantes :
     * <ul>
     *   <li>Sauvegarde temporairement le fichier reçu en tant que fichier d'entrée (ex. : input.txt).</li>
     *   <li>Lance un job Spring Batch en utilisant le fichier d'entrée et un chemin de sortie spécifié.</li>
     *   <li>Attend la fin du traitement du batch.</li>
     *   <li>Retourne le fichier transformé en tant que ressource téléchargeable.</li>
     * </ul>
     *
     * @param file Le fichier multipart contenant les données numériques ligne par ligne.
     * @param outputDirPath Le chemin du dossier où enregistrer le fichier de sortie (ex. : "target/output").
     * @return Une {@link ResponseEntity} contenant le fichier transformé sous forme de {@link org.springframework.core.io.Resource},
     *         ou une réponse HTTP 500 si une erreur est survenue.
     */
    ResponseEntity<Resource> transformFile(MultipartFile file, String outputDirPath);
}
