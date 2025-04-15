FROM openjdk:17

WORKDIR /payment-module

COPY target/*.jar payment-module.jar

EXPOSE 7078

ENTRYPOINT ["java","-jar","payment-module.jar"]