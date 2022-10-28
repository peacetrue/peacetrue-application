#!/bin/bash

echo '-------- build template start -------------'

sh generate-template.sh

cd "$workingDir/peacetrue-template" || exit
chmod +x gradlew
./gradlew peacetrue-template-webmvc:clean peacetrue-template-webmvc:build peacetrue-template-webmvc:publishToMavenLocal evaluate

cd "learn-java/learn-java-backend" || exit
chmod +x gradlew
./gradlew clean build bootRun &

open -a 'Google Chrome' http://localhost:8080/actuator

echo '-------- build template over -------------'
