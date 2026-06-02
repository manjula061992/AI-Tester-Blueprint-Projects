# TestCase Generator - Java Spring Boot Version

## Overview
This is a complete Java-based conversion of the Test Case Generator UI from Python/Flask to Spring Boot. The application provides a powerful REST API for generating test cases using an intelligent backend service.

## Architecture

### Project Structure
```
java-webapp/
├── pom.xml                           # Maven configuration with Spring Boot
├── src/
│   ├── main/
│   │   ├── java/com/aiblueprint/
│   │   │   ├── Application.java     # Spring Boot main application
│   │   │   ├── controller/
│   │   │   │   └── TestCaseController.java   # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── TestCaseGenerationService.java  # Business logic
│   │   │   ├── model/
│   │   │   │   ├── TestCase.java              # Test case DTO
│   │   │   │   ├── TestCaseResponse.java      # API response model
│   │   │   │   └── SystemStatus.java          # Status response model
│   │   │   └── config/
│   │   │       └── WebConfig.java             # Spring MVC configuration
│   │   └── resources/
│   │       └── application.properties  # Spring Boot properties
│   └── test/
│       └── java/...                  # Unit tests
└── target/                            # Build output
```

## Prerequisites

- **Java 17 or higher**
- **Maven 3.8+**
- **Git** (optional)

## Installation & Setup

### 1. Build the Project
Navigate to the java-webapp directory and build using Maven:

```bash
cd Project1TestCaseGenratore/java-webapp
mvn clean install -DskipTests
```

Expected output: `BUILD SUCCESS`

### 2. Run the Application

#### Option A: Using Maven
```bash
mvn spring-boot:run
```

#### Option B: Using JAR file
```bash
java -jar target/testcase-generator-1.0.0.jar
```

The application will start on **http://localhost:8080**

### 3. Access the UI

- **Dashboard**: `http://localhost:8080/dashboard`
- **Chat Generator**: `http://localhost:8080/`
- **Health Check**: `http://localhost:8080/api/health`

## API Endpoints

### 1. System Status
```
GET /api/status
```

**Response:**
```json
{
  "system": "operational",
  "timestamp": "2024-01-15T10:30:00",
  "copilot": {
    "connected": true,
    "status": "connected",
    "message": "GitHub Copilot is available"
  },
  "version": "1.0.0"
}
```

### 2. Generate Testcases
```
POST /api/generate
Content-Type: application/json

{
  "input": "Create testcases for app.vwo.com login page"
}
```

**Response:**
```json
{
  "id": "uuid-string",
  "timestamp": "2024-01-15T10:30:00",
  "input": "Create testcases for app.vwo.com login page",
  "testcases": [
    {
      "id": "TC_LOGIN_001",
      "title": "Valid Login with Email and Password",
      "description": "User logs in with valid credentials...",
      "arrange": {...},
      "act": [...],
      "assertions": {...},
      "priority": "high",
      "category": "positive",
      "codeSnippet": "..."
    }
  ],
  "summary": {
    "total": 5,
    "generatedAt": "2024-01-15T10:30:00",
    "source": "GitHub Copilot"
  }
}
```

### 3. Retrieve Testcases
```
GET /api/testcases/{id}
```

### 4. Export Testcases
```
GET /api/export/{id}
```
Returns: Python file with all test cases

### 5. Health Check
```
GET /api/health
```

## Java Classes Overview

### Application.java
- Entry point for Spring Boot application
- Initializes the application context

### TestCaseGenerationService.java
**Key Methods:**
- `generateTestcases(String scenarioDescription)`: Main generation logic
- `generateLoginTestcases()`: Specialized testcases for login scenarios
- `generateGenericTestcases()`: Fallback for other scenarios

**Features:**
- Intelligent scenario detection
- Template-based generation
- Support for login page testing
- Security test cases (SQL injection, etc.)

### TestCaseController.java
**Endpoints:**
- `/api/status` - System status
- `/api/generate` - Generate testcases
- `/api/testcases/{id}` - Retrieve specific testcases
- `/api/export/{id}` - Export as Python file
- `/api/health` - Health check

### Models
- **TestCase.java**: Individual test case with AAA pattern (Arrange-Act-Assert)
- **TestCaseResponse.java**: API response wrapper with summary
- **SystemStatus.java**: System status and Copilot connectivity info

### WebConfig.java
- CORS configuration for cross-origin requests
- Static resource handling
- Spring MVC configuration

## Configuration

### application.properties
```properties
spring.application.name=testcase-generator
server.port=8080
server.servlet.context-path=/

# Logging
logging.level.root=INFO
logging.level.com.aiblueprint=DEBUG

# Jackson configuration
spring.jackson.default-property-inclusion=non_null
spring.jackson.serialization.indent-output=true
```

### Modify Server Port
Edit `application.properties`:
```properties
server.port=9090
```

## Key Features

✅ **REST API**: Clean RESTful endpoints for all operations
✅ **Spring Boot**: Modern Java framework with auto-configuration
✅ **CORS Enabled**: Cross-origin requests supported
✅ **Lombok**: Reduces boilerplate code (getters, setters, constructors)
✅ **JSON Processing**: Jackson for serialization/deserialization
✅ **In-Memory Storage**: Fast test case retrieval
✅ **Error Handling**: Comprehensive error responses
✅ **Type-Safe**: Full type safety with Java
✅ **Testable**: Service-Controller separation for easy testing
✅ **Scalable**: Ready for database integration

## Example Usage

### Using cURL

**Generate testcases:**
```bash
curl -X POST http://localhost:8080/api/generate \
  -H "Content-Type: application/json" \
  -d '{"input":"Create testcases for app.vwo.com login page"}'
```

**Check status:**
```bash
curl http://localhost:8080/api/status
```

**Export testcases:**
```bash
curl http://localhost:8080/api/export/{id} > testcases.py
```

### Using JavaScript/Fetch

```javascript
// Generate testcases
const response = await fetch('/api/generate', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({ input: 'Create testcases for login page' })
});

const result = await response.json();
console.log(result);
```

## Database Integration (Future)

To add database support:

1. Add Spring Data JPA dependency to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

2. Create JPA Entity classes (instead of simple POJOs)
3. Create Repository interfaces extending JpaRepository
4. Update Service layer to use repositories

## Build & Deployment

### Build WAR for Tomcat
Modify `pom.xml` to use `<packaging>war</packaging>` and run:
```bash
mvn clean package
```
Deploy `target/testcase-generator-1.0.0.war` to Tomcat

### Build Docker Image
Create `Dockerfile`:
```dockerfile
FROM openjdk:17-slim
COPY target/testcase-generator-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t testcase-generator:1.0 .
docker run -p 8080:8080 testcase-generator:1.0
```

## Troubleshooting

### Port 8080 Already in Use
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill process (Windows)
taskkill /PID <PID> /F

# Or change port in application.properties
server.port=8081
```

### Build Fails
```bash
# Clear Maven cache
mvn clean

# Rebuild with verbose output
mvn clean install -X
```

### CORS Errors
Ensure WebConfig.java CORS settings include your frontend domain:
```java
registry.addMapping("/api/**")
    .allowedOrigins("http://localhost:3000", "http://example.com")
```

## Performance Optimization

### Caching Testcases
Add Spring Cache:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

### Async Processing
Use `@Async` for long-running operations:
```java
@Async
public CompletableFuture<List<TestCase>> generateAsync(String scenario) {
    // Generation logic
}
```

## Version Information

- **Spring Boot**: 3.2.0
- **Java**: 17+
- **Maven**: 3.8+
- **Lombok**: Latest
- **Jackson**: Latest

## Migration from Python

### Key Differences
- **Flask routes** → **Spring @RequestMapping**
- **Python dictionaries** → **Java POJOs**
- **Flask JSON response** → **Spring ResponseEntity**
- **Python string formatting** → **Java StringBuilder**
- **Environment variables** → **application.properties**

### Frontend Compatibility
The existing HTML/JS frontend works with minimal changes:
- API URLs remain the same structure: `/api/generate`, `/api/status`
- JSON response format is identical
- No changes needed to JavaScript files

## License
[Your License Here]

## Support
For issues and questions, contact the development team.
