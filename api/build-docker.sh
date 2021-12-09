#!/bin/bash
VERSION=$(grep "version = " build.gradle | awk '{print $3}' | sed "s/[']//g")
PROJECT_NAME=$(grep "rootProject.name = " settings.gradle | awk '{print $3}' | sed "s/[']//g")
BUILD_SPRING_BOOT=1
BUILD_DOCKER=1
DOCKER_PUSH=0
DEPLOY_LOCAL=0
LOCAL_TAGS=("predicted/$PROJECT_NAME:$VERSION")
REMOTE_TAGS=("docker.reigate.ac.uk:5000/predicted/$PROJECT_NAME:$VERSION" "docker.reigate.ac.uk:5000/predicted/$PROJECT_NAME:latest" )

TAG_LIST=""

for i in ${LOCAL_TAGS[@]}; do TAG_LIST="$TAG_LIST -t $i "; done
for i in ${REMOTE_TAGS[@]}; do TAG_LIST="$TAG_LIST -t $i "; done

if [ ! -n $VERSION ]; then
  echo "Version cannot be blank.";
else
  echo "Building predicted-grade API Version: $VERSION.";

  [ $BUILD_SPRING_BOOT -ne 0 ] && \
    echo "II - Build Spring Boot Application" && \
    ./gradlew build;

  if [ $BUILD_DOCKER -ne 0 ]; then
    echo "II - Build Docker Image"
    CMD="docker build --build-arg JAR_FILE=$PROJECT_NAME-$VERSION.jar $TAG_LIST .;"
    echo "Command: $CMD"
    eval $CMD
  fi

  if [ $DOCKER_PUSH -ne 0 ]; then
    echo "II - Pushing Docker Image to Repository"
    for i in ${REMOTE_TAGS[@]}; do
      CMD="docker push $i;"
      echo "Command: $CMD"
      eval "$CMD"
    done
  fi

  if [ $DEPLOY_LOCAL -ne 0 ]; then
    docker container rm predicted-$PROJECT_NAME --force;
    docker run --name predicted-$PROJECT_NAME -d \
      -p 8098:8080 \
      --env-file .env \
      predicted/$PROJECT_NAME:$VERSION;
  fi
fi
