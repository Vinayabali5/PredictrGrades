# Build Phase
FROM gradle:6.8.1 AS build

WORKDIR /build
COPY . /build/

RUN gradle -x test build

# Image Creation Phase
FROM openjdk:11

WORKDIR  /app

COPY ./env-file-var.sh .
RUN chmod 755 env-file-var.sh

COPY ./application-env.yml application.yml
COPY --from=build /build/build/libs/predicted-grades-api.jar /app/app.jar

ENTRYPOINT ["./env-file-var.sh", "java",  "-jar",  "app.jar"]
