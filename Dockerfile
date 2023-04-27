FROM openjdk:17-jdk-slim
ARG JARFILE=target/*.jar
COPY ./target/studentmcs-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 4100
ENTRYPOINT ["java","-jar","/app.jar"]

#CMD ["./mvnw", "spring-boot:run"]