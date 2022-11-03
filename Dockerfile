FROM openjdk:11
LABEL maintainer="branko_rovcanin@epam.com"
VOLUME /song-service
COPY ./target/song-service-0.0.1-SNAPSHOT.jar /usr/app/
EXPOSE 8082
WORKDIR /usr/app
ENTRYPOINT ["java","-jar", "song-service-0.0.1-SNAPSHOT.jar"]