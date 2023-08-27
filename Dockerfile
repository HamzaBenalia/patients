FROM openjdk:17-alpine
ENV PORT=8080
COPY target/*.jar ./app-patient.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app-patient.jar"]
