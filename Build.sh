#!/bin/bash

LOG_FILE="Build.log"
MAVEN_COMMAND="./mvnw clean package -DskipTests"

servicesLocation=("Secure-Health-Discovery-Service"
                    "../Secure-Health-Auth-Service"
                    "../Secure-Health-Doctor-Service"
                    "../Secure-Health-Patient-Service"
                    "../Secure-Health-Gateway-Service")

echo > $LOG_FILE

for service in "${servicesLocation[@]}"
do
    echo "\n\n*********************************************************************************************************" >> $LOG_FILE
    echo "********************************[ Building $service ]********************************" >> $LOG_FILE
    echo "*********************************************************************************************************" >> $LOG_FILE
    cd $service && $MAVEN_COMMAND  >> ../$LOG_FILE
    sleep 5
done
