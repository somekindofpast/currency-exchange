FROM maven:3.8.5-openjdk-17-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8080:8080
COPY --from=builder /app/target/currencyexchange-0.0.1-SNAPSHOT.jar ./currencyexchange.jar
CMD java -jar ./currencyexchange.jar