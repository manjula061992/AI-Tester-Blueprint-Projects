package com.aiblueprint.model;

import java.util.Map;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
    private String id;
    private String title;
    private String description;
    private Map<String, Object> arrange;
    private List<String> act;
    private Map<String, Object> assertions;
    private String priority;
    private String category;
    private String codeSnippet;
}
