FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} camel-rest.jar

ENTRYPOINT ["java","-jar","/camel-rest.jar"]