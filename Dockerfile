FROM openjdk:8

WORKDIR /app
ADD . /app

ARG GRADLE_VERSION=6.3
RUN wget https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip
RUN unzip gradle-$GRADLE_VERSION-bin.zip
RUN gradle-$GRADLE_VERSION/bin/gradle bootJar

EXPOSE 8080

CMD ["java", "-jar", "build/libs/nhk-1.0-SNAPSHOT.jar"]
