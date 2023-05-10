FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} loan-service.jar
ENTRYPOINT ["java","-jar","loan-service.jar"]
EXPOSE 9901