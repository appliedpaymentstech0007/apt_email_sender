# Use Maven image to build the project
FROM maven:3.8.4-openjdk-8 AS builder

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Build the WAR file using Maven
RUN mvn clean package -DskipTests

# Use an official Tomcat image to deploy the WAR file
FROM tomcat:9.0-jdk8-openjdk

# Set working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR file from the builder stage
COPY --from=builder /app/target/Spring-Email-API-0.0.1.war ROOT.war

# Expose the port used by Tomcat
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
