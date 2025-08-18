FROM openjdk:17-jdk-slim

WORKDIR /app

COPY pom.xml .
RUN ./mvnw dependency:go-offline

COPY . .

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/Salon-0.0.1-SNAPSHOT.jar"]