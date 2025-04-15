FROM openjdk:17

WORKDIR /data-collection

COPY target/*.jar data-collection.jar

EXPOSE 7071

ENTRYPOINT ["java","-jar","data-collection.jar"]