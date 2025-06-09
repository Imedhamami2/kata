
package com.example.kata;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestDataProvider {
    public static Stream<Arguments> provideFooBarQuixData() {
        return Stream.of(
            arguments(1, "1"),
            arguments(2,"2"),
            arguments(3, "FOOFOO"),
            arguments(5, "BARBAR"),
            arguments(7, "QUIX"),
            arguments(9, "FOO"),
            arguments(15, "FOOBARBAR"),
            arguments(17, "QUIX"),
            arguments(30, "FOOBARFOO"),
            arguments(33, "FOOFOOFOO"),
            arguments(35, "BARFOOBAR"),
            arguments(51, "FOOBAR"),
            arguments(53, "BARFOO"),
            arguments(57, "FOOBARQUIX"),
            arguments(75, "FOOBARQUIXBAR"),
            arguments(73, "QUIXFOO"),
            arguments(100, "BAR"),
            arguments(0, "FOOBAR")
        );
    }
}
