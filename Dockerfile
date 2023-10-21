FROM maven:3.8.6-eclipse-temurin-11

WORKDIR /app
ADD . /app

RUN mvn clean install -DskipTests=true spring-boot:repackage

EXPOSE 8080

CMD ["java", "-jar", "target/nhk-0.0.1-SNAPSHOT.jar"]
