package com.example.kata.service.impl;

import com.example.kata.service.TransformService;

public class TransformServiceImpl implements TransformService {
    @Override
    public String transform(int number) {
        if (number < 0 || number > 100) {
            throw new IllegalArgumentException("Le nombre doit être entre 0 et 100.");
        }
        StringBuilder result = new StringBuilder();
        if (number % 3 == 0) result.append("FOO");
        if (number % 5 == 0) result.append("BAR");
        char[] chars = String.valueOf(number).toCharArray();
        for (char c : chars) {
            if (c == '3') result.append("FOO");
            if (c == '5') result.append("BAR");
            if (c == '7') result.append("QUIX");
        }
        return result.isEmpty() ? String.valueOf(number) : result.toString();
    }
}