#!/bin/bash

PID=$(ps -ef | grep 'com.zlht.pbr.algorithm.developer.AlgorithmDeveloperApi' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No AlgorithmDeveloperApi process found to stop"
    exit 1
fi

echo "Stopping AlgorithmDeveloperApi process $PID"
kill $PID

exit 0
