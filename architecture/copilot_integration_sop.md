# ⚡ GitHub Copilot Integration SOP

## Purpose
Define the deterministic workflow for connecting Flask app to GitHub Copilot (local VS Code extension) to generate dynamic testcases.

---

## 🔗 Integration Architecture

### Connection Flow
```
User Input (Chat UI)
        ↓
Flask Backend (/api/generate)
        ↓
Copilot Connector (tools/copilot_connector.py)
        ↓
GitHub Copilot API / Local Extension
        ↓
Testcase Generation
        ↓
Format Output (tools/formatter.py)
        ↓
Return to Chat UI (JSON)
```

---

## 📥 Phase 2: Link Verification Steps

### Step 1: Check Copilot Availability
- Verify Copilot extension is installed in VS Code
- Check authentication token availability
- Validate API endpoint connectivity

### Step 2: Initialize Copilot Connector
- Load API credentials from `.env`
- Test connection with simple prompt
- Handle authentication errors gracefully

### Step 3: Test Prompt Generation
- Send minimal test prompt to Copilot
- Verify response format (JSON)
- Cache response for fallback

### Step 4: Error Handling Strategy
- If Copilot unavailable → Use mock/fallback testcases
- If API rate limited → Queue requests or use cache
- If authentication fails → Prompt user to configure credentials

---

## 🛠️ Implementation Components

### 1. Copilot Connector (`tools/copilot_connector.py`)
- Initialize Copilot API connection
- Build dynamic prompts from user input
- Parse Copilot responses into structured testcases
- Handle errors & fallbacks

### 2. Environment Variables (`.env`)
```
GITHUB_COPILOT_TOKEN=<user's github token>
GITHUB_COPILOT_MODEL=copilot-gpt4
COPILOT_API_ENDPOINT=https://api.github.com/copilot/chat
USE_MOCK_MODE=true (for development/testing)
```

### 3. Prompt Template
- Input: User scenario description
- Template: Structured prompt for Copilot
- Output: JSON-formatted testcases

---

## 🔄 Verification Checklist

- [ ] Copilot credentials configured in `.env`
- [ ] API connection successful (no 401/403 errors)
- [ ] Test prompt returns valid JSON
- [ ] Fallback mechanism works (mock testcases)
- [ ] Error messages user-friendly
- [ ] Rate limiting handled
- [ ] Logs capture all API calls

---

## 📊 Status Endpoints

### GET `/api/copilot/status`
Returns current Copilot connectivity status
```json
{
  "status": "connected|disconnected|error",
  "mode": "production|mock",
  "message": "Connected to GitHub Copilot"
}
```

### POST `/api/copilot/test`
Tests Copilot connection with sample prompt
```json
{
  "status": "success",
  "response_time_ms": 1234,
  "model": "copilot-gpt4"
}
```

---

## ⚠️ Known Limitations

1. **GitHub Copilot Chat API** requires specific authentication
2. **Rate limits** may apply depending on subscription
3. **Local extension mode** only works within VS Code
4. **Fallback testcases** may not be 100% specific to user input

## 🚀 Next Phase: Architect

Once Link is verified:
- Build `/tools/` atomic scripts
- Implement testcase formatting layer
- Create decision-making routing logic
