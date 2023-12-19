# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/CourierTrackingSystem-0.0.1-SNAPSHOT.jar /app/tracking.jar

# Specify the command to run on container start
CMD ["java", "-jar", "/app/tracking.jar"]