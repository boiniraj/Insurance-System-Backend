FROM openjdk:17

WORKDIR /config-server

COPY target/*.jar config-server.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","config-server.jar"]