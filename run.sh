#!/bin/bash

mvn clean package
java -jar target/morelang-0.0.1-jar-with-dependencies.jar test/main.morelang
