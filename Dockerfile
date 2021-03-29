FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER artemzi

# Возможно дополнительно стоит поставить -XX:MaxRAMFraction=2
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /

ARG MYSQL_HOST="172.18.0.4"
ENV MYSQL_HOST=${MYSQL_HOST}

ARG JAR_FILE=./supervisor-backend/build/libs/supervisor-backend.jar
COPY ${JAR_FILE} supervisor-backend.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/supervisor-backend.jar"]
EXPOSE 8132
