# Build jar
FROM eclipse-temurin:17 as build

COPY gradle/ /app/gradle/
COPY teams-api/springboot  /app/teams-api/springboot
COPY teams-core/  /app/teams-core
COPY settings.gradle gradlew /app/
RUN chmod +x /app/gradlew
WORKDIR /app/
RUN ./gradlew build -x test

FROM eclipse-temurin

RUN mkdir /opt/garage/
WORKDIR /opt/garage/
COPY --from=build /app/teams-api/springboot/build/libs/garage*.jar /opt/garage/garage.jar

ENV JAVA_OPTS="-Xmx2048m"
ENTRYPOINT [ "java", "-jar", "/opt/garage/garage.jar" ]
