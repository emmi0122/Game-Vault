#!/bin/bash
echo "Packaging Project..."
mvn clean package
echo "Package complete, running project..."
java -jar target/game-service-0.0.1-SNAPSHOT.jar