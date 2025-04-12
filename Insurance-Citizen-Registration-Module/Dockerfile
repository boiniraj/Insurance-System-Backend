FROM openjdk:17

WORKDIR /citizen-register

COPY target/*.jar citizen-register.jar

EXPOSE 6061


ENTRYPOINT ["java","-jar","citizen-register.jar"]