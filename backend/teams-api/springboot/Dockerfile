# Build jar
FROM adoptopenjdk/openjdk15:jdk-15.0.2_7-alpine as build

COPY config/ /app/config/
COPY gradle/ /app/gradle/
COPY src/ /app/src/
COPY build.gradle gradlew settings.gradle package.json package-lock.json webpack.config.js /app/
RUN chmod +x /app/gradlew
WORKDIR /app/

RUN ./gradlew build -x test

FROM adoptopenjdk/openjdk15:jre-15.0.2_7-alpine

RUN mkdir /opt/garage/
WORKDIR /opt/garage/
COPY --from=build /app/build/libs/garage*.jar /opt/garage/garage.jar

ENV JAVA_OPTS="-Xmx2048m"
ENTRYPOINT [ "java", "-jar", "/opt/garage/garage.jar" ]