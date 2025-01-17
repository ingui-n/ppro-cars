# Step 1: Use a base image with build tools (e.g., Maven or Gradle)
FROM maven:3.9-openjdk-23-slim AS build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy your project files to the container
COPY . .

# Step 4: Build the project
RUN mvn clean package -DskipTests

# Step 5: Use a minimal runtime image for the application
FROM openjdk:23-jdk-slim

# Step 6: Copy the built JAR from the build stage
COPY --from=build /app/target/pizzeria.jar app.jar

# Step 7: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
