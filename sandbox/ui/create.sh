#!/bin/sh
mvn archetype:create -DarchetypeGroupId=org.richfaces.cdk -DarchetypeArtifactId=maven-archetype-jsf-component -DarchetypeVersion=3.3.1-SNAPSHOT -DgroupId=org.richfaces.ui -DartifactId=${1}