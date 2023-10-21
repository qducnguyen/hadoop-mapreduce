#!/bin/bash

FOLDERNAME=`basename "$PWD"`;
docker compose up --build -d
docker exec ${FOLDERNAME}-namenode-1 sh -c "hdfs namenode -format"
docker exec -it ${FOLDERNAME}-namenode-1 bash
# start-all.sh

