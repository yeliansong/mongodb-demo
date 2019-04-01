#!/bin/bash
#Here's the command to run the app if you're using Java 9 and above
#java -cp /vagrant/target/aiq-vision-platform-java-*-jar-with-dependencies.jar --add-exports java.base/jdk.internal.ref=ALL-UNNAMED  aiq.vision.TrainTopology -debug

#Java 8 and below
java -cp /home/default/Documents/aiq/mongodb_demo/target/aiq-vision-mongodb-demo-*-jar-with-dependencies.jar mongodb_demo -debug
