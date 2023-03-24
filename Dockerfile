FROM openjdk:19
EXPOSE 8080
ADD target/products-service-queries.jar products-service-queries.jar
ENTRYPOINT [ "java", "-jar", "/products-service-queries.jar"]