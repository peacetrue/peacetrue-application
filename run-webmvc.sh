#!/bin/bash

if [ ! -f "peacetrue-application" ]; then
  git clone -b "1.0.0" https://github.com/peacetrue/peacetrue-application
fi

cd peacetrue-application || exit
./gradlew peacetrue-application-webmvc:bootRun
