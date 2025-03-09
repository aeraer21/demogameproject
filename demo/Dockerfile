FROM openjdk:21
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
EXPOSE 3306
EXPOSE 27017
CMD ["java", "-jar", "app.jar"]