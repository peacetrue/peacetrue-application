#!/bin/bash

./generate-template.sh
cd "$workingDir/peacetrue-template" || exit
./gradlew peacetrue-template-webmvc:clean
./gradlew peacetrue-template-webmvc:build
./gradlew peacetrue-template-webmvc:publishToMavenLocal
./gradlew evaluate

cd "learn-java/learn-java-backend" || exit
chmod +x gradlew
./gradlew clean build bootRun
