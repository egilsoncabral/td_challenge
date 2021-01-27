FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /opt/td-challenge.jar
ENTRYPOINT ["java","-jar","/opt/td-challenge.jar"]
