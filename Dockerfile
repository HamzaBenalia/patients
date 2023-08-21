FROM openjdk:17-alpine
WORKDIR /opt/patient
ENV PORT=8080
EXPOSE 8080
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

