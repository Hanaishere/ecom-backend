# Use official lightweight JDK image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Give execute permission to mvnw (important for Linux containers)
RUN chmod +x mvnw

# Download dependencies (cached if pom.xml unchanged)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the project (skip tests)
RUN ./mvnw package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the generated JAR file
CMD ["java", "-jar", "target/ecom-proj1-0.0.1-SNAPSHOT.jar"]
