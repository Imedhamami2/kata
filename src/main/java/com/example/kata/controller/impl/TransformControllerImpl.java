package com.example.kata.controller.impl;

import com.example.kata.controller.TransformApi;
import com.example.kata.service.TransformService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformControllerImpl implements TransformApi {

    private final TransformService service;

    public TransformControllerImpl(TransformService service) {
        this.service = service;
    }

    @Override
    public String transform(int number) {
        return service.transform(number);
    }
}
