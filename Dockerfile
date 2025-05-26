# Use Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from Maven target folder
COPY target/Cloud_Capstone_Backend*.jar app.jar

# Expose container port
EXPOSE 80

# Run Spring Boot JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
