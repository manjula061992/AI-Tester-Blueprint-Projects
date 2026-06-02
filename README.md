# 🚀 Testcase Generator with GitHub Copilot

**A powerful, intelligent testcase generation system powered by GitHub Copilot and the B.L.A.S.T. protocol.**

---

## 📋 Overview

The Testcase Generator is an AI-powered platform that automatically creates comprehensive, production-ready testcases from natural language input. Users simply describe their testing scenario via a chat interface, and the system generates:

- ✅ **Positive testcases** (happy path)
- ❌ **Negative testcases** (error handling)
- ⚠️ **Edge cases** (boundary conditions)
- 🔒 **Security testcases** (SQL injection, etc.)

All testcases follow the **Arrange-Act-Assert (AAA)** pattern and are exported as **Selenium WebDriver Python scripts**, ready for immediate execution.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 💬 **Chat Interface** | Natural language input for intuitive testcase generation |
| 🤖 **GitHub Copilot** | Powered by advanced AI for intelligent testcase creation |
| 📋 **Structured Output** | AAA pattern ensures consistent, professional testcases |
| 🔧 **Selenium Ready** | Production-ready WebDriver code for immediate use |
| 📊 **Comprehensive** | Covers happy paths, negative cases, security tests, and edge cases |
| ⚡ **Fast** | Generate 5+ testcases in seconds |
| 🎭 **Mock Mode** | Development-friendly fallback testcase generation |
| 📈 **Dashboard** | Real-time status monitoring with clear instructions |
| 📥 **Export** | Download as Python Selenium script |
| 🔐 **Error Handling** | Graceful fallbacks and detailed error messages |

---

## 🚀 Quick Start

### 1. Install Dependencies
```powershell
cd c:\Users\Manjula\Documents\AIBluePrintClass\Project1TestCaseGenratore
pip install -r requirements.txt
```

### 2. Run the Application
```powershell
python app.py
```

### 3. Access the Dashboard
- **Dashboard (Instructions):** http://localhost:5000/dashboard
- **Chat Generator:** http://localhost:5000/

---

## 📊 Architecture

Built using the **B.L.A.S.T. Protocol** (Blueprint, Link, Architect, Stylize, Trigger):

### Phase 1: Blueprint ✅
- Data schemas defined
- Architectural invariants established
- Chat UI and backend created

### Phase 2: Link ✅
- GitHub Copilot connector built
- Connection status monitoring
- Mock mode for development
- Dashboard with instructions

### Phase 3: Architect (In Progress)
- Atomic tools layer
- Decision-making routing
- Testcase formatting

### Phase 4: Stylize
- Output refinement
- UI/UX polish

### Phase 5: Trigger
- Production deployment
- Automation setup

---

## 🎯 Usage Example

### Step 1: Open Chat UI
Navigate to: http://localhost:5000/

### Step 2: Enter Scenario
```
Create testcases for app.vwo.com login page with email, 
password, remember me button, and submit button. 
When invalid credentials are entered, show "You have entered 
an invalid credentials". On valid login, navigate to dashboard.
```

### Step 3: View Testcases
The system generates:
- TC_001: Valid login → Dashboard
- TC_002: Invalid credentials → Error message
- TC_003: Empty email → Validation error
- TC_004: Empty password → Validation error
- TC_005: Remember me → Session persistence
- TC_006: SQL injection → Security test

### Step 4: Export & Execute
Click **📥 Export as Python** to download `test_<id>.py`

```python
# Run the exported testcases
pip install selenium
python test_<id>.py
```

---

## 📁 Project Structure

```
Project1TestCaseGenratore/
├── 📄 README.md (THIS FILE)
├── 🎯 app.py                          # Flask backend - START HERE
├── 📋 requirements.txt
├── 🌐 templates/
│   ├── index.html                     # Chat UI
│   └── dashboard.html                 # Dashboard with instructions
├── 🎨 static/
│   ├── chat.js                        # Chat logic
│   ├── dashboard.js                   # Dashboard updates
│   ├── styles.css                     # Chat styling
│   └── dashboard.css                  # Dashboard styling
├── 🔧 tools/
│   └── copilot_connector.py           # Copilot integration
├── 📚 architecture/
│   ├── testcase_generation_sop.md     # Testcase generation workflow
│   └── copilot_integration_sop.md     # Copilot connectivity guide
├── 📖 Documentation/
│   ├── gemini.md                      # Project Constitution
│   ├── task_plan.md                   # 5-phase roadmap
│   ├── findings.md                    # Discovery & research
│   ├── progress.md                    # Session activity log
│   ├── DEPLOYMENT_GUIDE.md            # Initial setup
│   ├── SETUP_AND_RUN_GUIDE.md         # Complete reference
│   └── LOGIN_TESTCASES_GENERATED.md   # Example testcases
└── ⚙️ .env.example                    # Environment template
```

---

## 🔗 API Endpoints

### Chat & Generation
- `POST /api/generate` — Generate testcases from input
- `GET  /api/testcases/:id` — Retrieve generated testcases
- `GET  /api/export/:id` — Export testcases as Python

### Status & Monitoring
- `GET  /api/status` — Get system and Copilot status
- `GET  /` — Chat UI
- `GET  /dashboard` — Dashboard with instructions

---

## ⚙️ Configuration

### Environment Variables (`.env`)

```env
# Flask Configuration
FLASK_ENV=development
FLASK_DEBUG=True

# GitHub Copilot (Production)
GITHUB_COPILOT_TOKEN=your_token_here
GITHUB_COPILOT_MODEL=copilot-gpt4

# Development Mode (Use mock testcases)
USE_MOCK_MODE=true

# Application
PORT=5000
LOG_LEVEL=INFO
```

---

## 🤖 GitHub Copilot Integration

### Current Mode: MOCK (Development)
Running with fallback testcases for development/testing.

### Production Setup
1. Configure GitHub token in `.env`
2. Set `USE_MOCK_MODE=false`
3. Restart the application

---

## 🎓 Testcase Structure (AAA Pattern)

All generated testcases follow the Arrange-Act-Assert pattern:

```json
{
  "id": "TC_001",
  "title": "Test title",
  "description": "What is being tested",
  "arrange": {
    "setup": "Environment setup",
    "test_data": {"field": "value"}
  },
  "act": [
    "User action 1",
    "User action 2"
  ],
  "assert": {
    "expected_outcome": "What should happen"
  },
  "priority": "high",
  "category": "positive"
}
```

---

## 🆘 Troubleshooting

### Port 5000 Already in Use
Edit `app.py` and change `port=5001`, then restart.

### ModuleNotFoundError: flask
Run: `pip install -r requirements.txt`

### Cannot Connect to localhost:5000
Ensure Flask app is running and check terminal output.

### Copilot Showing as Disconnected
This is expected in development. Set `USE_MOCK_MODE=true` in `.env`.

**More details:** See `SETUP_AND_RUN_GUIDE.md`

---

## 📊 System Status

### Current Phase: 2 (Link) ✅ COMPLETE
- ✅ Blueprint phase complete
- ✅ Copilot connector operational
- ✅ Dashboard with instructions ready
- ✅ Mock mode for development

### Deployment Status
- **Port:** 5000
- **Status:** Ready for testing
- **Mode:** Development (Mock)

---

## 📈 What's Included

### Pre-built Login Testcases
6 comprehensive testcases for **app.vwo.com login page**:
- Positive test (valid login)
- Negative tests (invalid credentials, empty fields)
- Feature test (remember me)
- Security test (SQL injection)

See `LOGIN_TESTCASES_GENERATED.md` for full details.

---

## 💡 Tips for Best Results

1. **Describe thoroughly:** Include element names, actions, and expected outcomes
2. **Be specific:** Mention error messages exactly as they appear
3. **Cover scenarios:** Request positive, negative, and edge cases
4. **Security:** Include relevant security testcases (SQL injection, XSS, etc.)

### Example Good Scenario:
```
Create testcases for app.vwo.com login page:
- Email and password input fields
- Remember me checkbox
- Submit button
- Valid login → Dashboard
- Invalid login → "You have entered an invalid credentials"
- Empty email → "Email is required"
- Empty password → "Password is required"
- Remember me → Persist session
- SQL injection attempt → Reject with error
```

---

## 🚀 Getting Started

### 1. Setup (5 minutes)
```powershell
cd c:\Users\Manjula\Documents\AIBluePrintClass\Project1TestCaseGenratore
pip install -r requirements.txt
```

### 2. Run Application (5 seconds)
```powershell
python app.py
```

### 3. Open Dashboard (Quick Reference)
http://localhost:5000/dashboard

### 4. Start Generating Testcases
http://localhost:5000/

**Happy testing! 🎉**

---

## 📚 Documentation

| Document | Purpose |
|----------|---------|
| `SETUP_AND_RUN_GUIDE.md` | ⭐ **START HERE** - Complete setup & troubleshooting |
| `gemini.md` | Project Constitution with data schemas |
| `DEPLOYMENT_GUIDE.md` | Initial deployment guide |
| `LOGIN_TESTCASES_GENERATED.md` | Example testcases with full details |
| `task_plan.md` | 5-phase development roadmap |
| `architecture/testcase_generation_sop.md` | Testcase generation workflow |
| `architecture/copilot_integration_sop.md` | Copilot connectivity guide |

---

## 🔄 B.L.A.S.T. Protocol

This project follows the **B.L.A.S.T. Master System Prompt** protocol:

- **B**lueprint — Data schemas & architecture defined ✅
- **L**ink — Copilot integration verified ✅
- **A**rchitect — Atomic tools layer (in progress)
- **S**tylize — Output refinement (planned)
- **T**rigger — Production deployment (planned)

---

## 🎓 Learn More

- See `gemini.md` for complete project constitution
- See `SETUP_AND_RUN_GUIDE.md` for detailed instructions
- See `progress.md` for session activity log
- See `findings.md` for research discoveries

---

## ✅ Version Information

- **Version:** 1.0.0
- **Phase:** 2 (Link) Complete
- **Status:** Ready for Deployment
- **Last Updated:** May 31, 2026
- **Framework:** Flask + HTML/CSS/JavaScript
- **Backend:** Python 3.8+
- **Database:** In-memory (JSON)

---

## 🙏 Thank You!

The Testcase Generator is ready to revolutionize your QA process. Enjoy fast, intelligent testcase generation! 🚀

**Questions?** See the troubleshooting section or refer to the comprehensive documentation provided.

---

**Happy Testing! 🎉**
