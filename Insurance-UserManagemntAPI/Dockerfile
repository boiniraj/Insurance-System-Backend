FROM openjdk:17

WORKDIR /user-management

COPY target/*.jar user-management.jar

EXPOSE 7079

ENTRYPOINT ["java","-jar","user-management.jar"]