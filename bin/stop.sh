#!/bin/bash

PID=$(ps -ef | grep 'com.zlht.pbr.algorithm.management.AlgorithmDeveloperApi' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No AlgorithmDeveloperApi process found to stop"
    exit 1
fi

echo "Stopping AlgorithmDeveloperApi process $PID"
kill -SIGTERM $PID

# Wait for up to 30 seconds for the process to stop
for i in {1..30}; do
    if ps -p $PID > /dev/null; then
        sleep 1
    else
        echo "AlgorithmDeveloperApi process $PID stopped"
        exit 0
    fi
done

echo "AlgorithmDeveloperApi process $PID did not stop in a timely manner, sending SIGKILL"
kill -SIGKILL $PID
exit 0
