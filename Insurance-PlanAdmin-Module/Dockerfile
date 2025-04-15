FROM openjdk:17

WORKDIR /plan-admin

COPY target/*.jar plan-admin.jar

EXPOSE 7073

ENTRYPOINT ["java","-jar","plan-admin.jar"]