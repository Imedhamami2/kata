package com.example.kata.service.impl;

import com.example.kata.service.TransformService;

public class TransformServiceImpl implements TransformService {
    @Override
    public String transform(int number) {
        return String.valueOf(number);
    }
}