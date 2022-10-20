FROM maven:3-eclipse-temurin-19-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
RUN mkdir /opt/mundialapp
WORKDIR /opt/mundialapp
COPY . /opt/mundialapp
ENTRYPOINT ["mvn","spring-boot:run"]
