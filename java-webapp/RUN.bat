@echo off
REM Test Case Generator - Auto Setup Script

echo ========================================
echo  TestCase Generator - Java Setup
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [!] Maven not found. Installing Maven...
    echo.
    
    REM Create maven directory
    if not exist "C:\maven" mkdir C:\maven
    
    REM Download Maven (Alternative: You can download manually)
    echo Please wait while Maven is being set up...
    echo.
    
    REM For now, let's try to use mvn from current directory or download
    powershell -Command "Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip' -OutFile 'C:\maven\maven.zip' -UseBasicParsing" 2>nul
    
    if exist "C:\maven\maven.zip" (
        echo Extracting Maven...
        powershell -Command "Expand-Archive -Path 'C:\maven\maven.zip' -DestinationPath 'C:\maven' -Force" 2>nul
        
        REM Add to PATH
        setx PATH "%PATH%;C:\maven\apache-maven-3.9.16\bin"
        
        echo Maven installed successfully!
        echo Please restart your terminal and run this script again.
        pause
        exit /b
    )
) else (
    echo [✓] Maven found
)

REM Check Java
java -version >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [!] Java not found. Please install Java 17+
    pause
    exit /b 1
)

echo [✓] Java found

REM Navigate to project
cd /d "C:\Users\Manjula\Documents\AIBluePrintClass\Project1TestCaseGenratore\java-webapp"

echo.
echo ========================================
echo  Building Project...
echo ========================================
echo.

call mvn clean install -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [!] Build failed. Check errors above.
    pause
    exit /b 1
)

echo.
echo ========================================
echo  Build Successful!
echo ========================================
echo.
echo Starting application on port 8080...
echo.

call mvn spring-boot:run

pause
