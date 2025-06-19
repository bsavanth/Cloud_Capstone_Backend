@echo off
SETLOCAL

REM --- Configuration ---
SET APP_NAME=Cloud_Capstone_Backend
SET IMAGE_NAME=bsavanth/cloud-capstone-backend-image
SET CONTAINER_NAME=cloud-capstone-backend-container-local
SET HOST_PORT=8080
SET CONTAINER_PORT=8080

REM --- Step 1: Build Spring Boot JAR with Maven ---
echo [1/3] Building Spring Boot project...
call mvn clean package -DskipTests

IF ERRORLEVEL 1 (
    echo Maven build failed. Exiting.
    exit /b 1
)

REM --- Step 2: Build Docker image ---
echo [2/3] Building Docker image...
docker build -t %IMAGE_NAME% .

IF ERRORLEVEL 1 (
    echo Docker image build failed. Exiting.
    exit /b 1
)

REM --- Step 3: Run Docker container ---
echo [3/3] Running Docker container...

REM Stop and remove existing container (if any)
docker stop %CONTAINER_NAME% >nul 2>&1
docker rm %CONTAINER_NAME% >nul 2>&1
REM docker run -d -p %HOST_PORT%:%CONTAINER_PORT% --name %CONTAINER_NAME% %IMAGE_NAME%

IF ERRORLEVEL 1 (
    echo Failed to run Docker container.
    exit /b 1
)

echo Container is running at http://localhost:%HOST_PORT%

ENDLOCAL
pause
