package com.aiblueprint.controller;

import com.aiblueprint.model.TestCase;
import com.aiblueprint.model.TestCaseResponse;
import com.aiblueprint.model.SystemStatus;
import com.aiblueprint.service.TestCaseGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TestCaseController {

    @Autowired
    private TestCaseGenerationService testCaseGenerationService;

    private Map<String, TestCaseResponse> testcasesStore = new HashMap<>();

    @GetMapping("/status")
    public ResponseEntity<SystemStatus> getStatus() {
        SystemStatus.CopilotStatus copilotStatus = new SystemStatus.CopilotStatus(
                true,
                "connected",
                "GitHub Copilot is available"
        );

        SystemStatus status = new SystemStatus(
                "operational",
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                copilotStatus,
                "1.0.0"
        );

        return ResponseEntity.ok(status);
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateTestcases(@RequestBody Map<String, String> request) {
        String input = request.get("input");

        if (input == null || input.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Input cannot be empty"));
        }

        try {
            List<TestCase> testcases = testCaseGenerationService.generateTestcases(input);

            String id = UUID.randomUUID().toString();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

            TestCaseResponse.Summary summary = new TestCaseResponse.Summary(
                    testcases.size(),
                    timestamp,
                    "GitHub Copilot"
            );

            TestCaseResponse response = new TestCaseResponse(
                    id,
                    timestamp,
                    input,
                    testcases,
                    summary
            );

            testcasesStore.put(id, response);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Failed to generate testcases: " + e.getMessage()));
        }
    }

    @GetMapping("/testcases/{id}")
    public ResponseEntity<?> getTestcases(@PathVariable String id) {
        TestCaseResponse response = testcasesStore.get(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/export/{id}")
    public ResponseEntity<?> exportTestcases(@PathVariable String id) {
        TestCaseResponse response = testcasesStore.get(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        StringBuilder pythonCode = new StringBuilder();
        pythonCode.append("#!/usr/bin/env python3\n");
        pythonCode.append("# Auto-generated testcases for: ").append(response.getInput()).append("\n");
        pythonCode.append("# Generated at: ").append(response.getTimestamp()).append("\n");
        pythonCode.append("# Total testcases: ").append(response.getTestcases().size()).append("\n\n");
        pythonCode.append("from selenium import webdriver\n");
        pythonCode.append("from selenium.webdriver.common.by import By\n");
        pythonCode.append("from selenium.webdriver.support.ui import WebDriverWait\n");
        pythonCode.append("from selenium.webdriver.support import expected_conditions as EC\n\n");

        for (TestCase tc : response.getTestcases()) {
            pythonCode.append("# ").append(tc.getId()).append(": ").append(tc.getTitle()).append("\n");
            pythonCode.append("# ").append(tc.getDescription()).append("\n");
            pythonCode.append(tc.getCodeSnippet()).append("\n\n");
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"testcases_" + id + ".py\"")
                .body(pythonCode.toString());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "TestCase Generator API"
        ));
    }
}
