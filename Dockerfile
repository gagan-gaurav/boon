# Using the base image with JDK18
FROM openjdk:18

# Setting the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/boon-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port for Spring Boot app.
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
