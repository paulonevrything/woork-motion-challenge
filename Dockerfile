FROM openjdk:8-jdk-alpine

ADD target/rest-api-0.0.1.jar rest-api-0.0.1.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "rest-api-0.0.1.jar"]
