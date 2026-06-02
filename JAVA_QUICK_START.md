# Quick Start Guide - Java Version

## 5-Minute Setup

### Prerequisites
- Java 17+ installed (`java -version` to check)
- Maven installed (`mvn -version` to check)

### Step 1: Navigate to Java Project
```bash
cd Project1TestCaseGenratore/java-webapp
```

### Step 2: Build the Project
```bash
mvn clean install -DskipTests
```
**Expected**: `BUILD SUCCESS`

### Step 3: Run the Application
```bash
mvn spring-boot:run
```

**Expected**: 
```
...
Started Application in 6.234 seconds (JVM running for 6.782)
2024-01-15 10:30:00.000  INFO ... : Tomcat started on port(s): 8080
```

### Step 4: Open in Browser
- **Dashboard**: http://localhost:8080/dashboard
- **Chat Interface**: http://localhost:8080/
- **Health Check**: http://localhost:8080/api/health

### Step 5: Test It Out
Enter in the chat or dashboard input:
```
Create testcases for app.vwo.com login page
```

**Expected**: 5 test cases generated (valid login, invalid credentials, empty email, empty password, SQL injection)

---

## Common Commands

### Start Server
```bash
mvn spring-boot:run
```

### Build Only (No Run)
```bash
mvn clean install
```

### Run Built JAR
```bash
java -jar target/testcase-generator-1.0.0.jar
```

### Stop Server
Press `Ctrl+C` in terminal

### View Logs
Logs appear in the console while running. For file-based logs:
```properties
# Add to application.properties
logging.file.name=logs/application.log
```

---

## API Quick Reference

### 1. Check System Status
```bash
curl http://localhost:8080/api/health
```

### 2. Generate Test Cases
```bash
curl -X POST http://localhost:8080/api/generate \
  -H "Content-Type: application/json" \
  -d "{\"input\":\"Create testcases for app.vwo.com login page\"}"
```

### 3. Export as Python
```bash
curl http://localhost:8080/api/export/{testcase-id} > testcases.py
```

---

## Project Structure

```
java-webapp/
├── src/main/java/com/aiblueprint/
│   ├── Application.java              ← Main app
│   ├── controller/TestCaseController.java    ← API endpoints
│   ├── service/TestCaseGenerationService.java ← Business logic
│   ├── model/TestCase.java           ← Data models
│   └── config/WebConfig.java         ← Configuration
├── src/main/resources/
│   ├── application.properties        ← Server config
│   └── static/                       ← HTML, CSS, JS (put files here)
├── pom.xml                           ← Maven config
└── target/                           ← Build output (JAR file)
```

---

## Change Server Port

Edit `src/main/resources/application.properties`:
```properties
server.port=9090
```

Then run and access: http://localhost:9090/

---

## Features Included

✅ **5 Login Test Cases**: Valid login, invalid credentials, empty fields, SQL injection
✅ **Generic Test Case Generation**: For any scenario
✅ **REST API**: All endpoints working
✅ **JSON Export**: Download as Python file
✅ **Status Monitoring**: Check Copilot and system status
✅ **In-Memory Storage**: Fast retrieval
✅ **Error Handling**: Comprehensive error responses
✅ **CORS Enabled**: Cross-origin requests allowed
✅ **Type Safe**: Full Java compile-time checking

---

## Next: Frontend Integration

The existing HTML/JS files work with minimal changes:

### Update JavaScript URLs
Edit `static/dashboard.js` and `static/chat.js`:
```javascript
// Change from:
const API_BASE = 'http://localhost:5000/api';

// To:
const API_BASE = 'http://localhost:8080/api';
```

Then copy the static files to the Java resources:
```bash
# Windows
xcopy ..\static src\main\resources\static\ /Y
xcopy ..\templates src\main\resources\static\ /Y

# Linux/Mac
cp -r ../static/* src/main/resources/static/
cp -r ../templates/* src/main/resources/static/
```

Rebuild and run:
```bash
mvn clean install
mvn spring-boot:run
```

---

## Deployment

### For Development
```bash
mvn spring-boot:run
```

### For Production
```bash
java -jar target/testcase-generator-1.0.0.jar --server.port=8080
```

### With Docker
```bash
docker build -t testcase-gen .
docker run -p 8080:8080 testcase-gen
```

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| **Maven not found** | Install Maven or add to PATH |
| **Java not found** | Install Java 17+ |
| **Port 8080 in use** | Change port in application.properties |
| **Build fails** | Run `mvn clean` first |
| **API returns 404** | Ensure server is running on port 8080 |

---

## Full Documentation

- **Java Version Details**: [java-webapp/JAVA_VERSION_README.md](java-webapp/JAVA_VERSION_README.md)
- **Frontend Integration**: [JAVA_FRONTEND_INTEGRATION.md](JAVA_FRONTEND_INTEGRATION.md)
- **Conversion Summary**: [PYTHON_TO_JAVA_CONVERSION.md](PYTHON_TO_JAVA_CONVERSION.md)

---

## Tips

💡 Keep the terminal running to see real-time logs
💡 Use `-DskipTests` for faster builds during development
💡 Check `application.properties` for all configuration options
💡 Add `-Xmx512m` to increase memory if needed

---

**Ready to go!** 🚀

Your Java Test Case Generator is now running on **http://localhost:8080/**
