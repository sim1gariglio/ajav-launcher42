#!/bin/bash

# Compile all Java files
find . -name "*.java" > sources.txt
javac @sources.txt
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

# Run the Simulator with the scenario file (change the path if needed)
java Simulator ../scenario.txt