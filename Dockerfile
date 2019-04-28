FROM openjdk:11-jre-slim

MAINTAINER joonak "ofnullable@gmail.com"

VOLUME /tmp

EXPOSE 8080

#ARG JAR_FILE
ADD /build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]