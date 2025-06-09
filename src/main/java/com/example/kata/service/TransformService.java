package com.example.kata.service;

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

}
