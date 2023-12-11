#!/bin/bash

#https://medium.com/@yiotiskl/boost-your-productivity-enabling-hot-reload-for-your-dockerized-spring-boot-kotlin-project-9aea60a53db1
(./gradlew -t :bootJar) &
./gradlew bootRun -PskipDownload=true