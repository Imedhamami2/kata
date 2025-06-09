package com.example.kata.service.impl;

import com.example.kata.service.TransformService;

public class TransformServiceImpl implements TransformService {
    @Override
    public String transform(int number) {
        StringBuilder result = new StringBuilder();
        if (number % 3 == 0) result.append("FOO");
        return result.isEmpty() ? String.valueOf(number) : result.toString();
    }
}