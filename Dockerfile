FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM maven:3.8.4-openjdk-17-slim
WORKDIR /app
COPY --from=builder /app/target/products-service-queries.jar products-service-queries.jar
ENTRYPOINT ["java", "-jar", "products-service-queries.jar"]