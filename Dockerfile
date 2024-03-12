FROM openjdk:17
EXPOSE 8080
WORKDIR /app
RUN mkdir -p /app/
ADD applications/app-service/build/libs/cuenta.jar /app/cuenta.jar
ENTRYPOINT ["java", "-jar", "/app/cuenta.jar"]
