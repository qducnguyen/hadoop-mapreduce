#!/bin/bash
FOLDERNAME=`basename "$PWD"`;
docker compose start
docker exec -it ${FOLDERNAME}-namenode-1 bash
# start-all.sh
