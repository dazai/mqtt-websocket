FROM openjdk:8
FROM mysql
EXPOSE 8080
ADD target/iot-0.0.1-SNAPSHOT.jar iot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "iot-0.0.1-SNAPSHOT.jar"]