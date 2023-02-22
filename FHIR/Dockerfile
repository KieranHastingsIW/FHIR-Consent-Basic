FROM maven:3.8.6-eclipse-temurin-17 as build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ src/
CMD ["mvn", "jetty:run"]
