FROM openjdk:17
LABEL author="brunorudy1"
WORKDIR /app
COPY  target/status-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]