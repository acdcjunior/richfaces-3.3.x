#!/bin/sh
mvn archetype:create -DarchetypeGroupId=org.richfaces.cdk -DarchetypeArtifactId=maven-archetype-jsfwebapp \
         -DarchetypeVersion=3.3.1-SNAPSHOT -Dversion=3.3.1-SNAPSHOT -DgroupId=org.richfaces.samples -DartifactId=$1
