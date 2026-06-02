# 🚀 Setup & Deployment Guide

## Prerequisites
- Python 3.8+
- pip (Python package manager)

---

## ⚙️ Installation Steps

### Step 1: Navigate to Project Directory
```powershell
cd c:\Users\Manjula\Documents\AIBluePrintClass\Project1TestCaseGenratore
```

### Step 2: Create Virtual Environment (Optional but Recommended)
```powershell
python -m venv venv
venv\Scripts\Activate
```

### Step 3: Install Dependencies
```powershell
pip install -r requirements.txt
```

---

## 🎯 Running the Application

### Start the Flask Server
```powershell
python app.py
```

Expected output:
```
 * Serving Flask app 'app'
 * Debug mode: on
 * Running on http://0.0.0.0:5000
```

### Access the Chat UI
Open your browser and navigate to:
```
http://localhost:5000
```

---

## 📋 Generated Login Page Testcases

The app includes **6 comprehensive testcases** for the app.vwo.com login page:

### ✅ Testcase 1: Valid Login (Positive Case)
- **ID:** TC_LOGIN_001
- **Scenario:** User logs in with valid credentials
- **Expected:** Navigate to dashboard
- **Priority:** High

### ❌ Testcase 2: Invalid Credentials Error
- **ID:** TC_LOGIN_002
- **Scenario:** User enters invalid email/password
- **Expected:** Error message "You have entered an invalid credentials"
- **Priority:** High

### ❌ Testcase 3: Empty Email Validation
- **ID:** TC_LOGIN_003
- **Scenario:** User submits form without email
- **Expected:** Validation error "Email is required"
- **Priority:** High

### ❌ Testcase 4: Empty Password Validation
- **ID:** TC_LOGIN_004
- **Scenario:** User submits form without password
- **Expected:** Validation error "Password is required"
- **Priority:** High

### ✅ Testcase 5: Remember Me Session Persistence
- **ID:** TC_LOGIN_005
- **Scenario:** User checks "Remember me" and closes browser
- **Expected:** Remains logged in upon reopening
- **Priority:** Medium

### 🔒 Testcase 6: SQL Injection Prevention
- **ID:** TC_LOGIN_006
- **Scenario:** Attacker attempts SQL injection in email field
- **Expected:** Rejected with "invalid credentials" message
- **Priority:** High (Security)

---

## 💬 Usage Example

1. Start the server (see above)
2. Open http://localhost:5000 in your browser
3. Enter: *"Create testcases for app.vwo.com login page with email, password, remember me button, and submit button"*
4. Click **Send**
5. View generated testcases in the right panel
6. Click **📥 Export as Python** to download Selenium script

---

## 📁 Project Structure
```
Project1TestCaseGenratore/
├── app.py                          # Flask backend
├── requirements.txt                # Python dependencies
├── templates/
│   └── index.html                  # Chat UI template
├── static/
│   ├── chat.js                     # Chat interaction logic
│   └── styles.css                  # UI styling
├── architecture/
│   └── testcase_generation_sop.md  # SOP documentation
├── task_plan.md
├── findings.md
├── progress.md
└── gemini.md                       # Project Constitution
```

---

## 🛑 Stopping the Server

Press **Ctrl+C** in the terminal running the Flask app.

---

## ✨ Features

- ✅ Chat-based interface for natural input
- ✅ 6 pre-built testcases for app.vwo.com login page
- ✅ Selenium WebDriver code generation
- ✅ Export to Python file
- ✅ Responsive, modern UI
- ✅ Real-time testcase display

---

## 🔗 API Endpoints

### POST `/api/generate`
Generate testcases from user input
```json
{
  "input": "Create testcases for app.vwo.com login page..."
}
```

### GET `/api/testcases/<testcase_id>`
Retrieve generated testcases by ID

### GET `/api/export/<testcase_id>`
Export testcases as Python Selenium script

---

## 📝 Notes

- All testcases follow the **Arrange-Act-Assert (AAA)** pattern
- Selenium code snippets are production-ready
- Error messages match the specification provided
- Security testcases included for SQL injection prevention

---

## 🆘 Troubleshooting

**Issue:** Port 5000 already in use
- **Solution:** Change port in `app.py` line: `app.run(port=5001)`

**Issue:** ModuleNotFoundError: No module named 'flask'
- **Solution:** Run `pip install flask`

**Issue:** Browser can't connect to localhost:5000
- **Solution:** Ensure the Flask app is running (check terminal output)
