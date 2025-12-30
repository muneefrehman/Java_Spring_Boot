# Use an official Java 17 runtime as base
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot jar
ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]