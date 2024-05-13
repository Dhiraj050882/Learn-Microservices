#Start with a base image containing Java Runtime
FROM openjdk:21-jdk-slim

#Information on who maintains the image
MAINTAINER learnmicroservices.com

#Add application's Jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]