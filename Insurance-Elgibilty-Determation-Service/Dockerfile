FROM openjdk:17

WORKDIR /eligibility-determination

COPY target/*.jar eligibility-determination.jar

EXPOSE 7072

ENTRYPOINT ["java","-jar","eligibility-determination.jar"]