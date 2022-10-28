#!/bin/bash

if [ ! -f "peacetrue-application" ]; then
  git clone -b "1.0.0" https://github.com/peacetrue/peacetrue-application
fi

cd peacetrue-application/peacetrue-application-react-admin || exit
pnpm install
pnpm start
