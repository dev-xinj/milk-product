FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY pom.xml ./
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

RUN ./mvnw dependency:go-offline -DskipTests

COPY src ./src

CMD [ "./mvnw","spring-boot:run"]
