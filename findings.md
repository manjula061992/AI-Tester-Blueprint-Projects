# 🔍 Findings & Discoveries

## Discovery Answers

| Question | Answer |
|----------|--------|
| **North Star** | Local LLM Testcase Generator - User provides input, system generates structured testcases |
| **Integrations** | GitHub Copilot API |
| **Source of Truth** | NA (User input is source) |
| **Delivery Payload** | Chat UI - User enters input → Copilot generates testcases |
| **Behavioral Rules** | Input-driven, local LLM, output structured testcases |

---

## Research & Constraints

### GitHub Copilot Integration
- [ ] Document Copilot API authentication method
- [ ] Identify available LLM models via Copilot
- [ ] Determine rate limits & usage constraints
- [ ] Verify local vs. cloud execution model

### Testcase Generation Template
- [ ] Define testcase structure (fields, hierarchy)
- [ ] Research best practices for testcase formatting
- [ ] Determine supported input types (code, requirements, specs)

### UI/Chat Implementation
- [ ] Technology choice: React, Vue, or vanilla JS?
- [ ] Chat message format & styling
- [ ] Error handling & user feedback

---

## Data Schema Requirements (To be finalized in gemini.md)

### Input Schema
```json
{
  "input_type": "code|requirements|specification",
  "content": "string",
  "language": "python|javascript|java|etc",
  "context": "optional string for additional details"
}
```

### Output Schema (Testcases)
```json
{
  "testcases": [
    {
      "id": "TC001",
      "title": "string",
      "description": "string",
      "inputs": {},
      "expected_output": "string",
      "edge_cases": ["string"]
    }
  ]
}
```
