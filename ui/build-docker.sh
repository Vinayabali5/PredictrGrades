#!/bin/bash
VERSION=$(jq -r '.version' package.json)
BUILD_NG=1
BUILD_DOCKER=1
DOCKER_PUSH=0
DEPLOY_LOCAL=0
LOCAL_TAGS=("predicted-grade-ui:$VERSION")
REMOTE_TAGS=("docker.reigate.ac.uk:5000/predicted-grade/predicted-grade/ui:$VERSION" "docker.reigate.ac.uk:5000/predicted-grade/predicted-grade/ui:latest" )

TAG_LIST=""

for i in ${LOCAL_TAGS[@]}; do TAG_LIST="$TAG_LIST -t $i "; done
for i in ${REMOTE_TAGS[@]}; do TAG_LIST="$TAG_LIST -t $i "; done

if [ ! -n $VERSION ]; then
  echo "Version cannot be blank.";
else
  echo "Building Predicted Grade UI Version: $VERSION.";

  [ $BUILD_NG -ne 0 ] && \
    echo "II - Build Angular Application" && \
    ng build --prod;

  if [ $BUILD_DOCKER -ne 0 ]; then
    echo "II - Build Docker Image"
    CMD="docker build $TAG_LIST .;"
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
    docker container rm predicted-grade-ui --force;
    docker run --name predicted-grade-ui -d \
      -p 8099:80 \
      --env-file .env  \
      predicted-grade-ui:$VERSION
  fi
fi
