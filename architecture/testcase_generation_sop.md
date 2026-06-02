# 🏛️ Testcase Generation SOP

## Purpose
Define the deterministic workflow for converting user input (code/requirements) into structured, production-ready testcases.

---

## 📥 Input Processing

### Step 1: Receive User Input
- Via Chat UI, user provides:
  - Scenario description (e.g., "Login page with email, password, remember me button")
  - Application URL (e.g., app.vwo.com)
  - Expected behaviors (valid & invalid cases)

### Step 2: Validate Input
- Check format: description is not empty
- Check URL validity
- Check for ambiguous requirements

### Step 3: Extract Requirements
- **Elements:** Fields, buttons, checkboxes
- **Happy Path:** Expected success behavior
- **Error Paths:** Error messages, validation rules
- **Edge Cases:** Empty fields, special characters, etc.

---

## 🔄 Testcase Generation Logic

### Template Structure (AAA Pattern)
```
ARRANGE: Setup element locators, test data
ACT: Perform user actions (click, type, submit)
ASSERT: Verify expected outcomes
```

### Testcase Categories
1. **Happy Path / Positive Cases** → Valid inputs → Success outcome
2. **Negative Cases** → Invalid inputs → Error message
3. **Edge Cases** → Boundary/special conditions → Handled gracefully
4. **Cross-browser / Accessibility** → Different browsers/accessibility needs

---

## 📤 Output Format

All testcases generated in **Selenium WebDriver** format (Python):
- Element locators (XPath, CSS, ID)
- Wait strategies (implicit/explicit waits)
- Assertion statements
- Error handling

---

## ⚙️ Implementation Tools
- **input_validator.py** → Validate input
- **testcase_generator.py** → Generate Selenium testcases
- **template_engine.py** → Apply AAA pattern
- **output_formatter.py** → Format to JSON/Python file
