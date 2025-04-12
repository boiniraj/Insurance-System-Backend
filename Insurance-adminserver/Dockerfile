FROM openjdk:17

WORKDIR /admin-server

EXPOSE 9999

COPY target/*.jar admin-server.jar

ENTRYPOINT ["java","-jar","admin-server.jar"]

