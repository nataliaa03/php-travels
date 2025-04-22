FROM jenkins/jenkins:lts

USER root
RUN apt-get update && apt-get install -y sudo maven openjdk-17-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

EXPOSE 8080

USER jenkins