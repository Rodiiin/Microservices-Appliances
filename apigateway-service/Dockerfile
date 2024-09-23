FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/apigateway-service-0.0.1.jar
COPY ${JAR_FILE} app_apigateway.jar
ENTRYPOINT ["java" , "-jar" , "app_apigateway.jar"]