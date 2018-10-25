#!/usr/bin/env bash
mvn clean package -U -Dmaven.test.skip=true
docker build -t hankzhangorion/bootu-order:latest .
docker push hankzhangorion/bootu-order:latest