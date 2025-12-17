#!/bin/bash
mvn clean package
java -jar ./target/review-service-0.0.1-SNAPSHOT.jar