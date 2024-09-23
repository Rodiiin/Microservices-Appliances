FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/carshop-service-0.0.1.jar
COPY ${JAR_FILE} app_carshop.jar
ENTRYPOINT ["java" , "-jar" , "app_carshop.jar"]
