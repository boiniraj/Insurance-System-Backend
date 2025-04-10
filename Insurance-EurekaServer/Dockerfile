FROM openjdk:17

WORKDIR /eureka-server

COPY target/*.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","eureka-server.jar"]