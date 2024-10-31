#!/bin/bash

echo "Stage 1: Running some command..."
# Simulate command success
echo "Command in stage 1 succeeded."
exit 0 # Return code 0 for success

echo "Stage 2: Running another command..."
# Simulate a command failure
echo "Command in stage 2 failed."
exit 1 # Return code 1 for failure

echo "Stage 3: Running yet another command..."
# Simulate another command success
echo "Command in stage 3 succeeded."
exit 0 # Return code 0 for success
