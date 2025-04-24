FROM jenkins/jenkins:lts

USER root

# Install tools and shell
RUN apt-get update && apt-get install -y \
    sudo \
    maven \
    openjdk-17-jdk \
    bash

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

EXPOSE 8080

# Switch back to jenkins user for safety
USER jenkins
