# 🚀 TESTCASE GENERATOR - COMPLETE SETUP & RUN GUIDE

**Version:** 1.0.0  
**Status:** ✅ Ready for Deployment  
**Phase:** 2 (Link) Complete  
**Date:** May 31, 2026

---

## 🎯 Quick Start (5 Minutes)

### Prerequisites
- Python 3.8+
- pip (Python package manager)
- Modern web browser

### Step 1: Navigate to Project
```powershell
cd c:\Users\Manjula\Documents\AIBluePrintClass\Project1TestCaseGenratore
```

### Step 2: Install Dependencies
```powershell
pip install -r requirements.txt
```

### Step 3: Run the Application
```powershell
python app.py
```

### Step 4: Access the Dashboard
Open in browser:
- **Dashboard (Instructions):** http://localhost:5000/dashboard
- **Chat Generator:** http://localhost:5000/

**That's it!** You're ready to generate testcases! 🎉

---

## 📊 System Components

### 1. Flask Backend (`app.py`)
- **Purpose:** REST API for testcase generation
- **Port:** 5000
- **Key Endpoints:**
  - `GET  /` - Chat UI
  - `GET  /dashboard` - Dashboard with instructions
  - `GET  /api/status` - System status
  - `POST /api/generate` - Generate testcases
  - `GET  /api/testcases/:id` - Retrieve testcases
  - `GET  /api/export/:id` - Export as Python

### 2. Frontend UI
- **Chat UI** (`templates/index.html`) - Chat-based input interface
- **Dashboard** (`templates/dashboard.html`) - Instructions & status monitoring
- **Styling** (`static/styles.css`, `static/dashboard.css`) - Modern, responsive design

### 3. Copilot Connector (`tools/copilot_connector.py`)
- **Purpose:** Interface with GitHub Copilot API
- **Features:**
  - Connection status monitoring
  - Mock mode for development
  - Fallback testcase generation
  - Error handling & logging
- **Mode:** Currently in MOCK mode (development)

---

## 📋 Using the Application

### Workflow A: Chat-Based Generation

1. **Open Chat UI**
   - Navigate to http://localhost:5000/

2. **Enter Your Scenario**
   - Example: "Create testcases for app.vwo.com login page with email, password, remember me button, and submit button"
   - Click **Send**

3. **View Generated Testcases**
   - Left panel: Chat history
   - Right panel: Generated testcases with:
     - Testcase ID & metadata
     - Arrange (setup/test data)
     - Act (test steps)
     - Assert (expected results)
     - Selenium code snippet

4. **Export Testcases**
   - Click **📥 Export as Python**
   - Downloads `test_<id>.py` file
   - Ready for execution with Selenium

### Workflow B: Dashboard Monitoring

1. **Open Dashboard**
   - Navigate to http://localhost:5000/dashboard

2. **Check System Status**
   - Copilot connectivity (Connected/Mock/Error)
   - Generator status (Ready)
   - Deployment status (Active on Port 5000)

3. **Follow Instructions**
   - 5-step guide for using the generator
   - Feature showcase
   - API documentation
   - Troubleshooting FAQ

---

## 🤖 GitHub Copilot Integration

### Current Status: MOCK MODE (Development)

The system is configured for development/testing with fallback testcases.

### Configuration (`.env`)

```env
# Set to false for production, true for development
USE_MOCK_MODE=true

# GitHub Copilot API credentials (for production)
GITHUB_COPILOT_TOKEN=your_github_token_here
GITHUB_COPILOT_MODEL=copilot-gpt4
GITHUB_COPILOT_API_ENDPOINT=https://api.github.com/copilot/chat
```

### Production Setup (Future)

When ready to integrate real GitHub Copilot API:
1. Set `USE_MOCK_MODE=false` in `.env`
2. Add GitHub token: `GITHUB_COPILOT_TOKEN=<your_token>`
3. Restart the app: `python app.py`

---

## 📁 Project Directory Structure

```
Project1TestCaseGenratore/
│
├── 🎯 Application Files
│   ├── app.py                       # Flask backend (START HERE)
│   ├── requirements.txt              # Python dependencies
│   └── .env.example                  # Environment template
│
├── 🎨 Frontend (Templates & Static)
│   ├── templates/
│   │   ├── index.html               # Chat UI
│   │   └── dashboard.html           # Dashboard with instructions
│   └── static/
│       ├── chat.js                  # Chat logic
│       ├── dashboard.js             # Dashboard status updates
│       ├── styles.css               # Chat styling
│       └── dashboard.css            # Dashboard styling
│
├── 🔧 Tools (Backend Logic)
│   └── tools/
│       └── copilot_connector.py     # Copilot integration
│
├── 📚 Architecture & Documentation
│   ├── architecture/
│   │   ├── testcase_generation_sop.md
│   │   └── copilot_integration_sop.md
│   ├── gemini.md                    # Project Constitution (Data schemas)
│   ├── task_plan.md                 # 5-phase roadmap
│   ├── findings.md                  # Discovery & research
│   ├── progress.md                  # Session activity log
│   ├── DEPLOYMENT_GUIDE.md          # Setup instructions
│   ├── LOGIN_TESTCASES_GENERATED.md # Generated testcase examples
│   └── SETUP_AND_RUN_GUIDE.md       # THIS FILE
```

---

## 🔗 API Reference

### 1. Get System Status
```
GET /api/status

Response:
{
  "system": "operational",
  "timestamp": "2026-05-31T...",
  "copilot": {
    "status": "mock",
    "mode": "mock",
    "model": "copilot-gpt4",
    "message": "🎭 Running in MOCK mode (for development)"
  },
  "version": "1.0.0"
}
```

### 2. Generate Testcases
```
POST /api/generate

Request:
{
  "input": "Create testcases for app.vwo.com login page..."
}

Response:
{
  "id": "uuid",
  "timestamp": "2026-05-31T...",
  "input": "...",
  "testcases": [
    {
      "id": "TC_001",
      "title": "...",
      "description": "...",
      "arrange": {...},
      "act": [...],
      "assert": {...},
      "priority": "high",
      "category": "positive"
    }
  ],
  "summary": {
    "total": 6,
    "source": "Fallback"
  }
}
```

### 3. Retrieve Testcases
```
GET /api/testcases/:id

Returns: Complete testcase details with code snippets
```

### 4. Export as Python
```
GET /api/export/:id

Response: Python Selenium script ready to execute
```

---

## ⚙️ Environment Variables

### File: `.env` (Create this from `.env.example`)

```env
# Flask Configuration
FLASK_ENV=development
FLASK_DEBUG=True
FLASK_APP=app.py

# Application Port
PORT=5000
HOST=0.0.0.0

# GitHub Copilot Configuration
GITHUB_COPILOT_TOKEN=your_github_token_here
GITHUB_COPILOT_MODEL=copilot-gpt4
GITHUB_COPILOT_API_ENDPOINT=https://api.github.com/copilot/chat

# Development Mode (Set to false for production)
USE_MOCK_MODE=true

# Logging
LOG_LEVEL=INFO
```

### Setup Steps:
```powershell
# 1. Copy example to .env
Copy-Item .env.example .env

# 2. Edit .env with your configuration
# 3. Restart the app for changes to take effect
```

---

## 🆘 Troubleshooting

### Issue 1: Port 5000 Already in Use
**Error:** `OSError: [Errno 10048] Only one usage of each socket address`

**Solution:**
```powershell
# Option A: Kill process on port 5000
Get-Process | Where-Object {$_.Name -eq "python"} | Stop-Process

# Option B: Change port in app.py
# Edit app.py, change: app.run(port=5001)
python app.py
```

### Issue 2: ModuleNotFoundError: No module named 'flask'
**Error:** `ModuleNotFoundError: No module named 'flask'`

**Solution:**
```powershell
pip install -r requirements.txt
```

### Issue 3: Cannot Connect to localhost:5000
**Error:** Browser shows "Connection refused"

**Solution:**
```powershell
# 1. Ensure Flask app is running in terminal
# 2. Check terminal output for errors
# 3. Verify port 5000 is available
# 4. Try: http://127.0.0.1:5000 instead of localhost:5000
```

### Issue 4: Copilot Showing as Disconnected
**Status:** ⚠️ "Copilot unavailable"

**Solution:**
- This is expected in MOCK mode (development)
- For production: Configure GitHub token in `.env`
- Change `USE_MOCK_MODE=false`
- Add your `GITHUB_COPILOT_TOKEN`

### Issue 5: Testcases Not Generating
**Error:** Empty testcase list

**Solution:**
```powershell
# 1. Ensure input is descriptive (20+ characters)
# 2. Check browser console for errors (F12)
# 3. Check Flask terminal output for exceptions
# 4. Try: "Create testcases for app.vwo.com login page"
```

### Issue 6: JavaScript Errors in Console
**Error:** `Uncaught TypeError: fetch is not defined`

**Solution:**
- Ensure you're using a modern browser (Chrome, Firefox, Safari, Edge)
- Check that Flask is running without errors
- Verify URL is correct: http://localhost:5000

---

## 📊 Pre-built Test Cases (Examples)

The system includes 6 pre-built testcases for **app.vwo.com login page**:

1. **TC_LOGIN_001** - Valid Login → Dashboard
2. **TC_LOGIN_002** - Invalid Credentials → Error message
3. **TC_LOGIN_003** - Empty Email → Validation error
4. **TC_LOGIN_004** - Empty Password → Validation error
5. **TC_LOGIN_005** - Remember Me → Session persistence
6. **TC_LOGIN_006** - SQL Injection → Security test

See `LOGIN_TESTCASES_GENERATED.md` for full details.

---

## 🚀 Running with Virtual Environment (Recommended)

### Create Virtual Environment
```powershell
python -m venv venv
```

### Activate Virtual Environment
```powershell
# Windows PowerShell
venv\Scripts\Activate

# Windows CMD
venv\Scripts\activate.bat

# Linux/Mac
source venv/bin/activate
```

### Install Dependencies in Virtual Environment
```powershell
pip install -r requirements.txt
```

### Run App (With Virtual Environment Active)
```powershell
python app.py
```

### Deactivate Virtual Environment (When Done)
```powershell
deactivate
```

---

## 📚 Documentation Files

| File | Purpose |
|------|---------|
| `gemini.md` | Project Constitution with data schemas |
| `task_plan.md` | 5-phase development roadmap |
| `findings.md` | Discovery answers & research |
| `progress.md` | Session activity log |
| `DEPLOYMENT_GUIDE.md` | Initial setup guide |
| `SETUP_AND_RUN_GUIDE.md` | This file - Complete reference |
| `LOGIN_TESTCASES_GENERATED.md` | Example testcases |
| `architecture/*.md` | SOPs for testcase generation & Copilot integration |

---

## 💡 Tips & Best Practices

1. **For Best Results:**
   - Provide detailed scenario descriptions
   - Include element names (email field, password field, etc.)
   - Specify expected behaviors (success, error messages)
   - Mention any special conditions (remember me, security tests)

2. **Example Scenario:**
   ```
   Create testcases for app.vwo.com login page with:
   - Email input field
   - Password input field
   - Remember me checkbox
   - Submit button
   
   Expected behaviors:
   - Valid login: Navigate to dashboard
   - Invalid login: Show error "You have entered an invalid credentials"
   - Empty fields: Show validation errors
   - Remember me: Persist session after browser close
   ```

3. **Export & Execute:**
   ```powershell
   # After exporting Python file:
   pip install selenium
   python test_<id>.py
   ```

4. **Check Logs:**
   - Flask terminal output shows all API calls
   - Browser console (F12) shows client-side errors
   - Check `.env` configuration if issues occur

---

## 🎓 Architecture Overview

```
User Input (Chat)
     ↓
Flask Backend (app.py)
     ↓
Copilot Connector (tools/copilot_connector.py)
     ↓
GitHub Copilot API (or Mock in development)
     ↓
Testcase Generation
     ↓
Format Output (AAA Pattern)
     ↓
Send to Frontend (JSON)
     ↓
Display in Chat UI / Export as Python
```

---

## 📞 Support & Next Steps

### Current Phase: 2 (Link) ✅ COMPLETE

**Next Phases:**
- **Phase 3:** Architect - Build atomic tools layer
- **Phase 4:** Stylize - Enhance output formatting
- **Phase 5:** Trigger - Production deployment

### Getting Help
1. Check **Troubleshooting** section above
2. Review **API Reference** for endpoint details
3. See **Workflow** section for step-by-step usage
4. Check **Tips & Best Practices** for input guidance

---

## 🎉 You're All Set!

The Testcase Generator is ready to use. Start by:

1. **Run the app:** `python app.py`
2. **Open dashboard:** http://localhost:5000/dashboard
3. **Read instructions** on the dashboard
4. **Go to chat:** http://localhost:5000/
5. **Start generating testcases!**

**Happy testing! 🚀**
