package com.example.kata.controller.impl;

import com.example.kata.controller.TransformApi;
import com.example.kata.service.TransformService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public ResponseEntity<Resource> transformFile(MultipartFile file, String outputDirPath) {
        return service.transformFile(file, outputDirPath);
    }


}
