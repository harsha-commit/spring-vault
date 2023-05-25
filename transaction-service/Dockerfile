FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} transactionservice.jar

ENTRYPOINT ["java","-jar","/transactionservice.jar"]

EXPOSE 8082