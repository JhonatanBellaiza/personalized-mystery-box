# Use a lightweight JDK base image
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the JAR file from Maven build
COPY target/custom-mystery-box-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]