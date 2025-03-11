# Use an official Tomcat image as the base image
FROM tomcat:9.0-jdk8-openjdk

# Set working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR file to the Tomcat webapps folder
COPY target/Spring-Email-API-0.0.1.war ROOT.war

# Expose the port Tomcat listens on (default is 8080)
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
