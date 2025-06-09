package com.example.kata.service.impl;

import com.example.kata.service.TransformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransformServiceImpl implements TransformService {

    @Override
    public String transform(int number) {
        log.info("Transform called with input: {}", number);
        if (number < 0 || number > 100) {
            log.warn("Invalid input: {}", number);
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

        String output = !result.isEmpty() ? result.toString() : String.valueOf(number);
        log.debug("Transformed result: {}", output);
        return output;
    }
}