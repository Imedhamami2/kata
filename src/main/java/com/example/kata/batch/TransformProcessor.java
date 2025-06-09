
package com.example.kata.batch;

import com.example.kata.service.TransformService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TransformProcessor implements ItemProcessor<String, String> {

    private final TransformService transformService;

    public TransformProcessor(TransformService transformService) {
        this.transformService = transformService;
    }

    @Override
    public String process(String item) {
        int number = Integer.parseInt(item.trim());
        return number + " " + transformService.transform(number);
    }
}