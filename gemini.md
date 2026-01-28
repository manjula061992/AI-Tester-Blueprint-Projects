# Project Constitution (gemini.md)

## Data Schemas

### Input (User -> System)
- **User Input**: String (Description of the feature code to test)
- **Settings**:
    - Model: `llama3.2` (Fixed)

### Internal Processing
- **Prompt Template**: stored in code.
    - Structure: `[System Instruction - Template] + [User Input]`

### Output (System -> User)
- **Test Cases**: Markdown formatted text containing the generated test cases.
- **Ollama API Payload** (Request):
    ```json
    {
      "model": "llama3.2",
      "prompt": "<Constructed Prompt>",
      "stream": false
    }
    ```
- **Ollama API Response**:
    ```json
    {
      "model": "llama3.2",
      "created_at": "...",
      "response": "<Generated Text>",
      "done": true
    }
    ```

## Behavioral Rules
1. **Model**: Strictly use `llama3.2`.
2. **Template**: Must use a predefined prompt template for consistency.
3. **UI**: Chat interface for input/output.
4. **Local Execution**: All processing happens via local Ollama instance (default port 11434).

## Architectural Invariants
1. **Frontend**: HTML/CSS (Vanilla) + JavaScript.
2. **Backend**: Direct connection to Local Ollama (User must ensure CORS is enabled on Ollama or we use a simple proxy if needed. *Plan A: Direct Fetch*).
3. **State**: Transient (Chat history not necessarily persisted unless requested, currently single turn request-response implied).

## Maintenance Log
- Initial Schema Defined.
