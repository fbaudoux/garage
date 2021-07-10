FROM openjdk:8-jdk-alpine
RUN mkdir /opt/dojo/
WORKDIR /opt/dojo/
ADD src/main/resources/application.properties /opt/dojo/application.properties
ENV JAVA_OPTS="-Xmx1024m"
ENTRYPOINT [ "java", "-jar", "/opt/dojo/dojo.jar" ]
ADD build/libs/dojo*.jar /opt/dojo/dojo.jar