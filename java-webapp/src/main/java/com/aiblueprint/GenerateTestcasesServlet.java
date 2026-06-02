package com.aiblueprint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenerateTestcasesServlet", urlPatterns = { "/generate" })
public class GenerateTestcasesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String scenario = request.getParameter("scenario");
        if (scenario == null) {
            scenario = "";
        }

        List<Map<String, Object>> testcases = generateTestcases(scenario);

        request.setAttribute("scenario", scenario);
        request.setAttribute("testcases", testcases);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private List<Map<String, Object>> generateTestcases(String scenario) {
        String normalized = scenario.toLowerCase();
        if (normalized.contains("login") && normalized.contains("app.vwo.com")) {
            return generateLoginTestcases();
        }
        return generateGenericTestcases(scenario);
    }

    private List<Map<String, Object>> generateLoginTestcases() {
        List<Map<String, Object>> testcases = new ArrayList<>();

        testcases.add(createTestcase(
                "TC_LOGIN_001",
                "Valid Login with Email and Password",
                "User logs in with valid credentials and should be navigated to dashboard",
                "high",
                "positive",
                List.of(
                        "Navigate to login page",
                        "Enter email: valid@example.com",
                        "Enter password: ValidPassword123",
                        "Leave 'Remember me' unchecked",
                        "Click Submit button"),
                Map.of(
                        "url", "https://app.vwo.com/login",
                        "email_field", "//input[@name='email']",
                        "password_field", "//input[@name='password']",
                        "remember_me_checkbox", "//input[@type='checkbox'][@name='remember']",
                        "submit_button", "//button[@type='submit']"),
                Map.of(
                        "expected_outcome", "User navigated to dashboard",
                        "url_contains", "/dashboard",
                        "page_title", "Dashboard"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='email']\")).sendKeys(\"valid@example.com\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='password']\")).sendKeys(\"ValidPassword123\");\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "new WebDriverWait(driver, Duration.ofSeconds(10))\n"
                        + "    .until(ExpectedConditions.urlContains(\"/dashboard\"));\n"
                        + "assert driver.getTitle().contains(\"Dashboard\");"));

        testcases.add(createTestcase(
                "TC_LOGIN_002",
                "Invalid Credentials - Error Message",
                "User enters invalid email/password and receives error message",
                "high",
                "negative",
                List.of(
                        "Navigate to login page",
                        "Enter email: invalid@example.com",
                        "Enter password: WrongPassword123",
                        "Click Submit button"),
                Map.of(
                        "url", "https://app.vwo.com/login",
                        "email_field", "//input[@name='email']",
                        "password_field", "//input[@name='password']",
                        "submit_button", "//button[@type='submit']",
                        "error_message", "//div[@class='error-message']"),
                Map.of(
                        "expected_outcome", "Error message displayed",
                        "error_text", "You have entered invalid credentials",
                        "user_remains_on_login_page", "true"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='email']\")).sendKeys(\"invalid@example.com\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='password']\")).sendKeys(\"WrongPassword123\");\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "new WebDriverWait(driver, Duration.ofSeconds(10))\n"
                        + "    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(\"//div[@class='error-message']\")));\n"
                        + "assert driver.findElement(By.xpath(\"//div[@class='error-message']\")).getText().contains(\"invalid credentials\");"));

        testcases.add(createTestcase(
                "TC_LOGIN_003",
                "Empty Email Field - Validation Error",
                "User tries to submit without entering email",
                "high",
                "negative",
                List.of(
                        "Navigate to login page",
                        "Leave email field empty",
                        "Enter password: SomePassword123",
                        "Click Submit button"),
                Map.of(
                        "url", "https://app.vwo.com/login"),
                Map.of(
                        "expected_outcome", "Validation error for empty email",
                        "error_message", "Email is required"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='password']\")).sendKeys(\"SomePassword123\");\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "new WebDriverWait(driver, Duration.ofSeconds(10))\n"
                        + "    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(\"//span[@class='validation-error']\")));\n"
                        + "assert driver.findElement(By.xpath(\"//span[@class='validation-error']\")).getText().contains(\"Email is required\");"));

        testcases.add(createTestcase(
                "TC_LOGIN_004",
                "Empty Password Field - Validation Error",
                "User tries to submit without entering password",
                "high",
                "negative",
                List.of(
                        "Navigate to login page",
                        "Enter email: valid@example.com",
                        "Leave password field empty",
                        "Click Submit button"),
                Map.of(
                        "url", "https://app.vwo.com/login"),
                Map.of(
                        "expected_outcome", "Validation error for empty password",
                        "error_message", "Password is required"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='email']\")).sendKeys(\"valid@example.com\");\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "new WebDriverWait(driver, Duration.ofSeconds(10))\n"
                        + "    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(\"//span[@class='validation-error']\")));\n"
                        + "assert driver.findElement(By.xpath(\"//span[@class='validation-error']\")).getText().contains(\"Password is required\");"));

        testcases.add(createTestcase(
                "TC_LOGIN_005",
                "Remember Me Checkbox - Session Persistence",
                "User checks 'Remember me' and session persists after closing browser",
                "medium",
                "positive",
                List.of(
                        "Navigate to login page",
                        "Enter valid credentials",
                        "Check 'Remember me'",
                        "Click Submit button",
                        "Re-open browser and verify session persists"),
                Map.of(
                        "url", "https://app.vwo.com/login"),
                Map.of(
                        "expected_outcome", "Session persists after closing browser",
                        "remember_me_checked", "true"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='email']\")).sendKeys(\"valid@example.com\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='password']\")).sendKeys(\"ValidPassword123\");\n"
                        + "driver.findElement(By.xpath(\"//input[@type='checkbox'][@name='remember']\")).click();\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "// Close and reopen browser manually to verify persistence"));

        testcases.add(createTestcase(
                "TC_LOGIN_006",
                "SQL Injection Attempt - Invalid Credentials Error",
                "User attempts SQL injection in email field",
                "high",
                "security",
                List.of(
                        "Navigate to login page",
                        "Enter email: ' OR '1'='1",
                        "Enter password: any",
                        "Click Submit button"),
                Map.of(
                        "url", "https://app.vwo.com/login"),
                Map.of(
                        "expected_outcome", "Login rejected and application remains secure",
                        "error_message", "Invalid credentials"),
                "WebDriver driver = new ChromeDriver();\n"
                        + "driver.get(\"https://app.vwo.com/login\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='email']\")).sendKeys(\"' OR '1'='1\");\n"
                        + "driver.findElement(By.xpath(\"//input[@name='password']\")).sendKeys(\"any\");\n"
                        + "driver.findElement(By.xpath(\"//button[@type='submit']\")).click();\n"
                        + "new WebDriverWait(driver, Duration.ofSeconds(10))\n"
                        + "    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(\"//div[@class='error-message']\")));\n"
                        + "assert driver.findElement(By.xpath(\"//div[@class='error-message']\")).getText().contains(\"Invalid credentials\");"));

        return testcases;
    }

    private List<Map<String, Object>> generateGenericTestcases(String scenario) {
        List<Map<String, Object>> testcases = new ArrayList<>();
        testcases.add(createTestcase(
                "TC_GENERIC_001",
                "Basic Scenario Validation",
                "Validate the scenario description and expected behavior",
                "medium",
                "positive",
                List.of("Review the scenario", "Document expected behavior", "Validate the success path"),
                Map.of("scenario", scenario),
                Map.of("expected_outcome", "Scenario is clearly described and testable"),
                "// This is a placeholder for generated testcase code"));
        return testcases;
    }

    private Map<String, Object> createTestcase(String id,
            String title,
            String description,
            String priority,
            String category,
            List<String> act,
            Map<String, String> arrange,
            Map<String, String> assertResults,
            String codeSnippet) {
        Map<String, Object> testcase = new HashMap<>();
        testcase.put("id", id);
        testcase.put("title", title);
        testcase.put("description", description);
        testcase.put("priority", priority);
        testcase.put("category", category);
        testcase.put("act", act);
        testcase.put("arrange", arrange);
        testcase.put("assert", assertResults);
        testcase.put("code_snippet", codeSnippet);
        return testcase;
    }
}
