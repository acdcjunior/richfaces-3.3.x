#!/bin/sh
mvn archetype:create -DarchetypeGroupId=org.richfaces.cdk -DarchetypeArtifactId=maven-archetype-jsfwebapp \
         -DarchetypeVersion=3.3.4-SNAPSHOT -Dversion=3.3.4-SNAPSHOT -DgroupId=org.richfaces.samples -DartifactId=$1
