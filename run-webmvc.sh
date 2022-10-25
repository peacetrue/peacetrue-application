#!/bin/bash

git clone -b "1.0.0" https://github.com/peacetrue/peacetrue-application
cd peacetrue-application || exit
./gradlew peacetrue-application-webmvc:bootRun
