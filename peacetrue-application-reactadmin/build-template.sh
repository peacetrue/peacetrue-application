#!/bin/bash

echo '-------- build template start -------------'

sh generate-template.sh

cd "$workingDir/peacetrue-template" || exit
chmod +x gradlew
./gradlew peacetrue-template-reactadmin:clean peacetrue-template-reactadmin:build peacetrue-template-reactadmin:publishToMavenLocal evaluate
./gradlew evaluate

cd "learn-java/learn-java-frontend" || exit
kill-by-port 3000
pnpm install
pnpm start

# open -a 'Google Chrome' http://localhost:3000

echo '-------- build template over -------------'
