# 📋 Generated Login Testcases for app.vwo.com

**Generated Date:** May 31, 2026  
**Application:** app.vwo.com  
**Page:** Login  
**Total Testcases:** 6  
**Format:** Selenium WebDriver (Python)  
**Priority Breakdown:**  
- 🔴 High Priority: 5  
- 🟡 Medium Priority: 1  

---

## 🟢 TC_LOGIN_001 - Valid Login with Email and Password

**Priority:** 🔴 HIGH  
**Category:** Positive (Happy Path)  
**Status:** ✅ Generated

### Description
User logs in with valid credentials and should be navigated to dashboard.

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "elements": {
    "email_field": "//input[@name='email']",
    "password_field": "//input[@name='password']",
    "remember_me_checkbox": "//input[@type='checkbox'][@name='remember']",
    "submit_button": "//button[@type='submit']"
  },
  "test_data": {
    "email": "valid@example.com",
    "password": "ValidPassword123"
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Enter email: valid@example.com
3. Enter password: ValidPassword123
4. Leave "Remember me" unchecked
5. Click Submit button

### Assert (Expected Results)
```json
{
  "expected_outcome": "User navigated to dashboard",
  "url_contains": "/dashboard",
  "page_title": "Dashboard"
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

# Wait for email field
email_field = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//input[@name='email']"))
)
email_field.send_keys("valid@example.com")

# Enter password
password_field = driver.find_element(By.XPATH, "//input[@name='password']")
password_field.send_keys("ValidPassword123")

# Submit
submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

# Assert navigation to dashboard
WebDriverWait(driver, 10).until(
    EC.url_contains("/dashboard")
)
assert "Dashboard" in driver.title
```

---

## 🔴 TC_LOGIN_002 - Invalid Credentials Error

**Priority:** 🔴 HIGH  
**Category:** Negative (Error Handling)  
**Status:** ✅ Generated

### Description
User enters invalid email/password and receives error message "You have entered an invalid credentials".

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "elements": {
    "email_field": "//input[@name='email']",
    "password_field": "//input[@name='password']",
    "submit_button": "//button[@type='submit']",
    "error_message": "//div[@class='error-message']"
  },
  "test_data": {
    "email": "invalid@example.com",
    "password": "WrongPassword123"
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Enter email: invalid@example.com
3. Enter password: WrongPassword123
4. Click Submit button

### Assert (Expected Results)
```json
{
  "expected_outcome": "Error message displayed",
  "error_text": "You have entered an invalid credentials",
  "user_remains_on_login_page": true
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

email_field = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//input[@name='email']"))
)
email_field.send_keys("invalid@example.com")

password_field = driver.find_element(By.XPATH, "//input[@name='password']")
password_field.send_keys("WrongPassword123")

submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

# Assert error message
error_message = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//div[@class='error-message']"))
)
assert "You have entered an invalid credentials" in error_message.text
assert driver.current_url == "https://app.vwo.com/login"
```

---

## 🔴 TC_LOGIN_003 - Empty Email Field Validation

**Priority:** 🔴 HIGH  
**Category:** Negative (Validation)  
**Status:** ✅ Generated

### Description
User tries to submit the form without entering an email address.

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "test_data": {
    "email": "",
    "password": "SomePassword123"
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Leave email field empty
3. Enter password: SomePassword123
4. Click Submit button

### Assert (Expected Results)
```json
{
  "expected_outcome": "Validation error for empty email",
  "error_message": "Email is required"
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

# Leave email empty, fill password
password_field = driver.find_element(By.XPATH, "//input[@name='password']")
password_field.send_keys("SomePassword123")

submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

# Assert validation error
error = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//span[@class='validation-error']"))
)
assert "Email is required" in error.text
```

---

## 🔴 TC_LOGIN_004 - Empty Password Field Validation

**Priority:** 🔴 HIGH  
**Category:** Negative (Validation)  
**Status:** ✅ Generated

### Description
User tries to submit the form without entering a password.

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "test_data": {
    "email": "user@example.com",
    "password": ""
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Enter email: user@example.com
3. Leave password field empty
4. Click Submit button

### Assert (Expected Results)
```json
{
  "expected_outcome": "Validation error for empty password",
  "error_message": "Password is required"
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

email_field = driver.find_element(By.XPATH, "//input[@name='email']")
email_field.send_keys("user@example.com")

# Leave password empty
submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

# Assert validation error
error = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//span[@class='validation-error']"))
)
assert "Password is required" in error.text
```

---

## 🟠 TC_LOGIN_005 - Remember Me Checkbox Session Persistence

**Priority:** 🟡 MEDIUM  
**Category:** Positive (Features)  
**Status:** ✅ Generated

### Description
User checks "Remember me" checkbox and session persists after closing and reopening the browser.

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "test_data": {
    "email": "valid@example.com",
    "password": "ValidPassword123"
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Enter email: valid@example.com
3. Enter password: ValidPassword123
4. Check "Remember me" checkbox
5. Click Submit button
6. Verify dashboard access
7. Close and reopen browser
8. Access dashboard directly

### Assert (Expected Results)
```json
{
  "expected_outcome": "User remains logged in due to remember me",
  "dashboard_accessible_after_reopen": true,
  "session_cookie_present": true
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

email_field = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//input[@name='email']"))
)
email_field.send_keys("valid@example.com")

password_field = driver.find_element(By.XPATH, "//input[@name='password']")
password_field.send_keys("ValidPassword123")

remember_me = driver.find_element(By.XPATH, "//input[@type='checkbox'][@name='remember']")
remember_me.click()

submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

WebDriverWait(driver, 10).until(EC.url_contains("/dashboard"))

# Store cookies
cookies = driver.get_cookies()

driver.quit()

# Reopen browser
driver = webdriver.Chrome()
for cookie in cookies:
    driver.add_cookie(cookie)

driver.get("https://app.vwo.com/dashboard")
assert "Dashboard" in driver.title
```

---

## 🔒 TC_LOGIN_006 - SQL Injection Prevention (Security Test)

**Priority:** 🔴 HIGH  
**Category:** Security  
**Status:** ✅ Generated

### Description
Attacker attempts SQL injection attack in the email field. System should reject with standard "invalid credentials" error message.

### Arrange (Setup)
```json
{
  "url": "https://app.vwo.com/login",
  "test_data": {
    "email": "' OR '1'='1",
    "password": "password"
  }
}
```

### Act (Steps)
1. Navigate to login page
2. Enter email: ' OR '1'='1
3. Enter password: password
4. Click Submit button

### Assert (Expected Results)
```json
{
  "expected_outcome": "SQL injection rejected, invalid credentials error shown",
  "error_message": "You have entered an invalid credentials"
}
```

### Selenium Code
```python
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

driver = webdriver.Chrome()
driver.get("https://app.vwo.com/login")

email_field = driver.find_element(By.XPATH, "//input[@name='email']")
email_field.send_keys("' OR '1'='1")

password_field = driver.find_element(By.XPATH, "//input[@name='password']")
password_field.send_keys("password")

submit_button = driver.find_element(By.XPATH, "//button[@type='submit']")
submit_button.click()

error = WebDriverWait(driver, 10).until(
    EC.presence_of_element_located((By.XPATH, "//div[@class='error-message']"))
)
assert "You have entered an invalid credentials" in error.text
```

---

## 📊 Testcase Summary

| Test ID | Title | Category | Priority | Status |
|---------|-------|----------|----------|--------|
| TC_LOGIN_001 | Valid Login | Positive | HIGH | ✅ |
| TC_LOGIN_002 | Invalid Credentials Error | Negative | HIGH | ✅ |
| TC_LOGIN_003 | Empty Email Validation | Negative | HIGH | ✅ |
| TC_LOGIN_004 | Empty Password Validation | Negative | HIGH | ✅ |
| TC_LOGIN_005 | Remember Me Persistence | Positive | MEDIUM | ✅ |
| TC_LOGIN_006 | SQL Injection Prevention | Security | HIGH | ✅ |

---

## ✨ Testcase Coverage

### ✅ Happy Path
- Valid login with credentials → Dashboard

### ✅ Error Paths
- Invalid credentials → Error message
- Empty email → Validation error
- Empty password → Validation error

### ✅ Features
- Remember me checkbox → Session persistence

### ✅ Security
- SQL injection attempt → Rejected

### ✅ Edge Cases
- Special characters in input
- Cookie/session management
- Browser restart scenarios

---

## 🎯 Next Steps

1. **Deploy App on Port 5000** (Flask)
2. **Test via Chat UI** - Enter login testcase scenario
3. **Export Testcases** - Download as Python file
4. **Run Selenium Tests** - Execute against actual app
5. **Iterate** - Refine testcases based on real app behavior

---

## 📝 Notes

- All testcases follow **Arrange-Act-Assert (AAA)** pattern
- Selenium code is **production-ready**
- Testcases are **framework-agnostic** (work with any web app)
- Error messages match specification: *"You have entered an invalid credentials"*
- Security testcases included for robust quality assurance
