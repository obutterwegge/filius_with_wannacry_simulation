#!/bin/zsh
mvn clean
mvn compile
mvn package
cp -r ./src/main/resources/* ./target
