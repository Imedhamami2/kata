
package com.example.kata.batch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BatchIntegrationTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job transformJob;

    @Test
    public void testBatchTransformsFile() throws Exception {
        Path input = Path.of("input.txt");
        Path output = Path.of("output/output.txt");

        // Préparation d'un fichier d'entrée
        Files.write(input, List.of("3", "5", "15"));

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("outputPath", output.toString())
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(transformJob, jobParameters);
        while (execution.isRunning()) {
            Thread.sleep(100);
        }

        assertTrue(Files.exists(output), "Output file should be created");

        List<String> lines = Files.readAllLines(output);
        assertTrue(lines.contains("3 FOOFOO"));
        assertTrue(lines.contains("5 BARBAR"));
        assertTrue(lines.contains("15 FOOBARBAR"));
    }
}
