FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} accountservice.jar

ENTRYPOINT ["java","-jar","/accountservice.jar"]

EXPOSE 8080