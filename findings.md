# Findings

## Research
- **Ollama API**: Runs on port 11434. Endpoint `/api/generate`.
- **Model**: `llama3.2`.

## Discoveries
- **Tech Stack**: Vanilla HTML/CSS/JS selected for "UI Chat".
- **Integration**: Direct client-side fetch to Ollama.
    - *Constraint*: Ollama usually blocks CORS by default. User might need to run `OLLAMA_ORIGINS="*" ollama serve` or we use a simple proxy. *Decision*: We will start with direct fetch and instruct user on CORS if it fails, or provide a simple Node proxy if preferred.

## Constraints
- Local environment only.
- User needs Ollama installed and model pulled.
