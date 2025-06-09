package com.example.kata.service.impl;

import com.example.kata.service.TransformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransformServiceImpl implements TransformService {

    private final JobLauncher jobLauncher;

    private final JobRegistry jobRegistry;

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

    @Override
    public ResponseEntity<Resource> transformFile(MultipartFile file, String outputDirPath) {
        try {
            log.info("Received file for transformation: {}", file.getOriginalFilename());
            Path inputPath = Paths.get("input.txt");
            Files.copy(file.getInputStream(), inputPath, StandardCopyOption.REPLACE_EXISTING);
            log.debug("Saved uploaded file to {}", inputPath);

            Path outputDir = Paths.get(outputDirPath);
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
                log.debug("Created output dir: {}", outputDir);
            }

            Path outputFile = outputDir.resolve("output.txt");
            JobParameters params = new JobParametersBuilder()
                    .addString("outputPath", outputFile.toString())
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            log.info("Launching batch job with output path: {}", outputFile);
            Job job = jobRegistry.getJob("transformJob");
            JobExecution execution = jobLauncher.run(job, params);
            while (execution.isRunning()) Thread.sleep(100);
            log.info("Batch job finished with status: {}", execution.getStatus());

            if (!Files.exists(outputFile)) {
                log.error("Batch job failed to produce output file");
                return ResponseEntity.internalServerError().build();
            }

            InputStreamResource resource =
                    new InputStreamResource(new FileInputStream(outputFile.toFile()));
            log.info("Returning transformed file: {}", outputFile);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.txt")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(resource);
        } catch (Exception e) {
            log.error("Exception during file transformation", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}