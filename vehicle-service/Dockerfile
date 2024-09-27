FROM openjdk:17
LABEL author="bruno-rudy"
WORKDIR /app
COPY  target/vehicle-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]