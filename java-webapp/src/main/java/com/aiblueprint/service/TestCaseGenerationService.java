package com.aiblueprint.service;

import com.aiblueprint.model.TestCase;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestCaseGenerationService {

    public List<TestCase> generateTestcases(String scenarioDescription) {
        if (scenarioDescription == null || scenarioDescription.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String normalized = scenarioDescription.toLowerCase();
        if (normalized.contains("login") && normalized.contains("app.vwo.com")) {
            return generateLoginTestcases();
        }

        return generateGenericTestcases(scenarioDescription);
    }

    private List<TestCase> generateLoginTestcases() {
        List<TestCase> testcases = new ArrayList<>();

        // TC_LOGIN_001: Valid Login
        testcases.add(createTestCase(
                "TC_LOGIN_001",
                "Valid Login with Email and Password",
                "User logs in with valid credentials and should be navigated to dashboard",
                createArrange("https://app.vwo.com/login",
                        Map.of(
                                "email_field", "//input[@name='email']",
                                "password_field", "//input[@name='password']",
                                "remember_me_checkbox", "//input[@type='checkbox'][@name='remember']",
                                "submit_button", "//button[@type='submit']"
                        ),
                        Map.of(
                                "email", "valid@example.com",
                                "password", "ValidPassword123"
                        )),
                List.of(
                        "Navigate to login page",
                        "Enter email: valid@example.com",
                        "Enter password: ValidPassword123",
                        "Leave 'Remember me' unchecked",
                        "Click Submit button"
                ),
                Map.of(
                        "expected_outcome", "User navigated to dashboard",
                        "url_contains", "/dashboard",
                        "page_title", "Dashboard"
                ),
                "high",
                "positive",
                getLoginValidCodeSnippet()
        ));

        // TC_LOGIN_002: Invalid Credentials
        testcases.add(createTestCase(
                "TC_LOGIN_002",
                "Invalid Credentials - Error Message",
                "User enters invalid email/password and receives error message",
                createArrange("https://app.vwo.com/login",
                        Map.of(
                                "email_field", "//input[@name='email']",
                                "password_field", "//input[@name='password']",
                                "submit_button", "//button[@type='submit']",
                                "error_message", "//div[@class='error-message']"
                        ),
                        Map.of(
                                "email", "invalid@example.com",
                                "password", "WrongPassword123"
                        )),
                List.of(
                        "Navigate to login page",
                        "Enter email: invalid@example.com",
                        "Enter password: WrongPassword123",
                        "Click Submit button"
                ),
                Map.of(
                        "expected_outcome", "Error message displayed",
                        "error_text", "You have entered an invalid credentials",
                        "user_remains_on_login_page", true
                ),
                "high",
                "negative",
                getLoginInvalidCodeSnippet()
        ));

        // TC_LOGIN_003: Empty Email Field
        testcases.add(createTestCase(
                "TC_LOGIN_003",
                "Empty Email Field - Validation Error",
                "User tries to submit without entering email",
                createArrange("https://app.vwo.com/login",
                        Map.of(),
                        Map.of(
                                "email", "",
                                "password", "SomePassword123"
                        )),
                List.of(
                        "Navigate to login page",
                        "Leave email field empty",
                        "Enter password: SomePassword123",
                        "Click Submit button"
                ),
                Map.of(
                        "expected_outcome", "Validation error for empty email",
                        "error_message", "Email is required"
                ),
                "high",
                "negative",
                getEmptyEmailCodeSnippet()
        ));

        // TC_LOGIN_004: Empty Password Field
        testcases.add(createTestCase(
                "TC_LOGIN_004",
                "Empty Password Field - Validation Error",
                "User tries to submit without entering password",
                createArrange("https://app.vwo.com/login",
                        Map.of(),
                        Map.of(
                                "email", "user@example.com",
                                "password", ""
                        )),
                List.of(
                        "Navigate to login page",
                        "Enter email: user@example.com",
                        "Leave password field empty",
                        "Click Submit button"
                ),
                Map.of(
                        "expected_outcome", "Validation error for empty password",
                        "error_message", "Password is required"
                ),
                "high",
                "negative",
                getEmptyPasswordCodeSnippet()
        ));

        // TC_LOGIN_005: SQL Injection Test
        testcases.add(createTestCase(
                "TC_LOGIN_005",
                "SQL Injection Protection",
                "System should safely handle SQL injection attempts",
                createArrange("https://app.vwo.com/login",
                        Map.of(),
                        Map.of(
                                "email", "' OR '1'='1",
                                "password", "' OR '1'='1"
                        )),
                List.of(
                        "Navigate to login page",
                        "Enter SQL injection payload in email",
                        "Enter SQL injection payload in password",
                        "Click Submit button"
                ),
                Map.of(
                        "expected_outcome", "Login fails safely without exposing DB errors",
                        "security_validated", true
                ),
                "high",
                "security",
                getSQLInjectionCodeSnippet()
        ));

        return testcases;
    }

    private List<TestCase> generateGenericTestcases(String scenario) {
        List<TestCase> testcases = new ArrayList<>();

        testcases.add(createTestCase(
                "TC_GEN_001",
                "Basic Positive Test Case",
                "Standard positive test case for: " + scenario,
                createArrange("https://example.com", Map.of(), Map.of()),
                List.of("Navigate to the application", "Perform the action as described"),
                Map.of("expected_outcome", "Action completes successfully"),
                "medium",
                "positive",
                "// Add Selenium WebDriver code here\n"
                        + "// For scenario: " + scenario
        ));

        testcases.add(createTestCase(
                "TC_GEN_002",
                "Negative Test Case",
                "Error handling test case for: " + scenario,
                createArrange("https://example.com", Map.of(), Map.of()),
                List.of("Navigate to the application", "Perform action with invalid data"),
                Map.of("expected_outcome", "Error is handled gracefully"),
                "medium",
                "negative",
                "// Add Selenium WebDriver code for error handling\n"
                        + "// For scenario: " + scenario
        ));

        return testcases;
    }

    private TestCase createTestCase(String id, String title, String description,
                                   Map<String, Object> arrange, List<String> act,
                                   Map<String, Object> assertions, String priority,
                                   String category, String codeSnippet) {
        TestCase tc = new TestCase();
        tc.setId(id);
        tc.setTitle(title);
        tc.setDescription(description);
        tc.setArrange(arrange);
        tc.setAct(act);
        tc.setAssertions(assertions);
        tc.setPriority(priority);
        tc.setCategory(category);
        tc.setCodeSnippet(codeSnippet);
        return tc;
    }

    private Map<String, Object> createArrange(String url, Map<String, String> elements,
                                             Map<String, Object> testData) {
        Map<String, Object> arrange = new HashMap<>();
        arrange.put("url", url);
        arrange.put("elements", elements);
        arrange.put("test_data", testData);
        return arrange;
    }

    private String getLoginValidCodeSnippet() {
        return "from selenium import webdriver\n"
                + "from selenium.webdriver.common.by import By\n"
                + "from selenium.webdriver.support.ui import WebDriverWait\n"
                + "from selenium.webdriver.support import expected_conditions as EC\n\n"
                + "driver = webdriver.Chrome()\n"
                + "driver.get(\"https://app.vwo.com/login\")\n\n"
                + "# Wait for email field\n"
                + "email_field = WebDriverWait(driver, 10).until(\n"
                + "    EC.presence_of_element_located((By.XPATH, \"//input[@name='email']\"))\n"
                + ")\n"
                + "email_field.send_keys(\"valid@example.com\")\n\n"
                + "# Enter password\n"
                + "password_field = driver.find_element(By.XPATH, \"//input[@name='password']\")\n"
                + "password_field.send_keys(\"ValidPassword123\")\n\n"
                + "# Submit\n"
                + "submit_button = driver.find_element(By.XPATH, \"//button[@type='submit']\")\n"
                + "submit_button.click()\n\n"
                + "# Assert navigation to dashboard\n"
                + "WebDriverWait(driver, 10).until(EC.url_contains(\"/dashboard\"))\n"
                + "assert \"Dashboard\" in driver.title";
    }

    private String getLoginInvalidCodeSnippet() {
        return "from selenium import webdriver\n"
                + "from selenium.webdriver.common.by import By\n"
                + "from selenium.webdriver.support.ui import WebDriverWait\n"
                + "from selenium.webdriver.support import expected_conditions as EC\n\n"
                + "driver = webdriver.Chrome()\n"
                + "driver.get(\"https://app.vwo.com/login\")\n\n"
                + "email_field = WebDriverWait(driver, 10).until(\n"
                + "    EC.presence_of_element_located((By.XPATH, \"//input[@name='email']\"))\n"
                + ")\n"
                + "email_field.send_keys(\"invalid@example.com\")\n\n"
                + "password_field = driver.find_element(By.XPATH, \"//input[@name='password']\")\n"
                + "password_field.send_keys(\"WrongPassword123\")\n\n"
                + "submit_button = driver.find_element(By.XPATH, \"//button[@type='submit']\")\n"
                + "submit_button.click()\n\n"
                + "error_message = WebDriverWait(driver, 10).until(\n"
                + "    EC.presence_of_element_located((By.XPATH, \"//div[@class='error-message']\"))\n"
                + ")\n"
                + "assert \"You have entered an invalid credentials\" in error_message.text";
    }

    private String getEmptyEmailCodeSnippet() {
        return "from selenium import webdriver\n"
                + "from selenium.webdriver.common.by import By\n\n"
                + "driver = webdriver.Chrome()\n"
                + "driver.get(\"https://app.vwo.com/login\")\n\n"
                + "# Leave email empty\n"
                + "password_field = driver.find_element(By.XPATH, \"//input[@name='password']\")\n"
                + "password_field.send_keys(\"SomePassword123\")\n\n"
                + "submit_button = driver.find_element(By.XPATH, \"//button[@type='submit']\")\n"
                + "submit_button.click()\n\n"
                + "# Verify validation error\n"
                + "error = WebDriverWait(driver, 10).until(\n"
                + "    EC.presence_of_element_located((By.XPATH, \"//span[@class='validation-error']\"))\n"
                + ")\n"
                + "assert \"Email is required\" in error.text";
    }

    private String getEmptyPasswordCodeSnippet() {
        return "from selenium import webdriver\n"
                + "from selenium.webdriver.common.by import By\n\n"
                + "driver = webdriver.Chrome()\n"
                + "driver.get(\"https://app.vwo.com/login\")\n\n"
                + "# Fill email only\n"
                + "email_field = driver.find_element(By.XPATH, \"//input[@name='email']\")\n"
                + "email_field.send_keys(\"user@example.com\")\n\n"
                + "# Leave password empty\n"
                + "submit_button = driver.find_element(By.XPATH, \"//button[@type='submit']\")\n"
                + "submit_button.click()\n\n"
                + "# Verify validation error\n"
                + "error = WebDriverWait(driver, 10).until(\n"
                + "    EC.presence_of_element_located((By.XPATH, \"//span[@class='validation-error']\"))\n"
                + ")\n"
                + "assert \"Password is required\" in error.text";
    }

    private String getSQLInjectionCodeSnippet() {
        return "from selenium import webdriver\n"
                + "from selenium.webdriver.common.by import By\n\n"
                + "driver = webdriver.Chrome()\n"
                + "driver.get(\"https://app.vwo.com/login\")\n\n"
                + "# Test SQL injection protection\n"
                + "email_field = driver.find_element(By.XPATH, \"//input[@name='email']\")\n"
                + "email_field.send_keys(\"' OR '1'='1\")\n\n"
                + "password_field = driver.find_element(By.XPATH, \"//input[@name='password']\")\n"
                + "password_field.send_keys(\"' OR '1'='1\")\n\n"
                + "submit_button = driver.find_element(By.XPATH, \"//button[@type='submit']\")\n"
                + "submit_button.click()\n\n"
                + "# Should not bypass login\n"
                + "assert \"Dashboard\" not in driver.title";
    }
}
