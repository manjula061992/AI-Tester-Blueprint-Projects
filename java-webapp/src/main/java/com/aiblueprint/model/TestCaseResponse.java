package com.aiblueprint.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseResponse {
    private String id;
    private String timestamp;
    private String input;
    private List<TestCase> testcases;
    private Summary summary;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private int total;
        private String generatedAt;
        private String source;
    }
}
