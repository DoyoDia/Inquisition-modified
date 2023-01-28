FROM openjdk:11-jre-slim
MAINTAINER DazeCake

RUN apt-get -y update
RUN apt-get -y install android-tools-adb

COPY build/libs/*.jar /Inquisition.jar
COPY src/main/resources/application.yml /config/application.yml

EXPOSE 2000

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Shanghai","--illegal-access=deny", "/Inquisition.jar"]