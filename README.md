# B.L.A.S.T. Local Test Generator

This application uses a local Ollama instance to generate test cases.

## Prerequisites
1. **Ollama** installed.
2. **Model** pulled: `ollama pull llama3.2` (This is currently running in the background if authorized).

## How to Run

### 1. Configure Ollama for Browser Access (CORS)
Run this command in PowerShell to allow the app to connect:
```powershell
$env:OLLAMA_ORIGINS="*"; ollama serve
```

### 2. Run the Application
Since this requires a web server, utilize the included script:
```powershell
powershell -ExecutionPolicy Bypass -File .\tools\server.ps1
```
# B.L.A.S.T. Local Test Generator

This application uses a local Ollama instance to generate test cases.

## Prerequisites
1. **Ollama** installed.
2. **Model** pulled: `ollama pull llama3.2` (This is currently running in the background if authorized).

## How to Run

### 1. Configure Ollama for Browser Access (CORS)
Run this command in PowerShell to allow the app to connect:
```powershell
$env:OLLAMA_ORIGINS="*"; ollama serve
```

### 2. Run the Application
Since this requires a web server, utilize the included script:
```powershell
powershell -ExecutionPolicy Bypass -File .\tools\server.ps1
```
Then open **[http://localhost:8080](http://localhost:8080)** in your browser.

## Features
- **Modern UI**: Dark glassmorphism design.
- **Local AI**: Uses `llama3.2` for privacy and speed.
- **Markdown Support**: Nicely formatted test cases.
# AI Tester Blueprint Projects