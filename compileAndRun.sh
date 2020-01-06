#!/bin/bash

#pass in the main class name $1

javac -d bin src/*.java
java -cp ./bin $1