FROM openjdk:8-jdk
MAINTAINER Seands <seands@126.com>

ENTRYPOINT ["java", "-jar", "/data/app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /data/app.jar
EXPOSE 7002