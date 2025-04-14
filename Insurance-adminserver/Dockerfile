FROM openjdk:17

WORKDIR /admin-server

COPY target/*.jar admin-server.jar

EXPOSE 9999

ENTRYPOINT ["java","-jar","admin-server.jar"]