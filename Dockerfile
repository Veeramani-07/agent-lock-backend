# Stage 1: Build JAR using Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Dynamic search for pom.xml location and build
RUN POM_DIR=$(dirname $(find . -name "pom.xml" | head -n 1)) && \
    cd $POM_DIR && \
    mvn clean package -DskipTests && \
    mkdir -p /app/target && \
    cp target/*.jar /app/target/

# Stage 2: Run Spring Boot App
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
