#!/bin/bash
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_74.jdk/Contents/Home"
MAVEN_HOME="/Applications/Netbeans/NetBeans 8.1.app/Contents/Resources/NetBeans/java/maven"
MAVEN=$MAVEN_HOME/bin
PATH=$PATH:$MAVEN

##########################################################
# package acs-core
##########################################################
mvn io.takari:maven:wrapper