package com.newche;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class BackJobScheduler {
    Logger logger = LoggerFactory.getLogger(BackJobScheduler.class);

    @Scheduled(cron = "0 5 * * * *")
    public void runDailyTasks() {
        logger.info("Daily - Backjobs Started...");

        executeTask("src/main/resources/python/webScraping/sciencealert_ws.py", "Science Alert", "Daily");
        executeTask("src/main/resources/python/webScraping/spacenews_ws.py", "Space News", "Daily");
        executeTask("src/main/resources/python/webScraping/motorsport_ws.py", "Motorsport", "Daily");
        
        logger.info("Daily - Backjobs END...");
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE, initialDelay = 5000)
    public void runStartupTasks() {
        logger.info("Startup - Backjobs Started...");

        executeTask("src/main/resources/python/webScraping/sciencealert_ws.py", "Science Alert", "StartUp");
        executeTask("src/main/resources/python/webScraping/spacenews_ws.py", "Space News", "StartUp");
        executeTask("src/main/resources/python/webScraping/motorsport_ws.py", "Motorsport", "StartUp");

        logger.info("Startup - Backjobs END...");
    }

    private void executeTask(String scriptPath, String taskName, String timeTask) {
        logger.info("{} - Executing task for: {}...", timeTask, taskName);
        runPythonScript(scriptPath);
        logger.info("{} - Task completed for: {}...", timeTask, taskName);
    }

    private void runPythonScript(String scriptPath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.debug(line); 
                }
                int exitCode = process.waitFor();
                logger.info("Exited with code: {}", exitCode); // Use logger with placeholder
            }
        } catch (Exception e) {
            logger.error("An error occurred while running the Python script: ", e);
        }
    }
}
