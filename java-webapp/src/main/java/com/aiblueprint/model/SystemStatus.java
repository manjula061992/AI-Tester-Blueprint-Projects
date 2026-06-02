package com.aiblueprint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemStatus {
    private String system;
    private String timestamp;
    private CopilotStatus copilot;
    private String version;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CopilotStatus {
        private boolean connected;
        private String status;
        private String message;
    }
}
