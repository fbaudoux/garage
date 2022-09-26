# Build jar
FROM eclipse-temurin:11 as build

COPY config/ /app/config/
COPY gradle/ /app/gradle/
COPY src/ /app/src/
COPY settings.gradle build.gradle gradlew /app/
RUN chmod +x /app/gradlew
WORKDIR /app/

RUN ./gradlew build -x test

FROM eclipse-temurin

RUN mkdir /opt/garage/
WORKDIR /opt/garage/
COPY --from=build /app/build/libs/garage*.jar /opt/garage/garage.jar

ENV JAVA_OPTS="-Xmx2048m"
ENTRYPOINT [ "java", "-jar", "/opt/garage/garage.jar" ]