cdi: How to test CDI components
======================================================
Author: Arquillian team
Level: Intermediate
Technologies: Arquillian, CDI, JUnit
Summary: Examples that demonstrate how to test CDI components using Arquillian
Prerequisites: 
Target Product: WFK
Source: <https://github.com/arquillian/arquillian-showcase>


What is it?
-----------

This quickstart code contains a lot of CDI examples with its features like Decorator, Events, Qualifiers, etc.

There's also many Arquillian code that demonstrates how to test each one of these CDI features.


System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7. 


Start JBoss Enterprise Application Platform 6 or JBoss AS 7
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat



Run the Arquillian Tests 
------------------------

_NOTE: The following commands assume you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Run the Arquillian Tests](../../README.md#run-the-arquillian-tests) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:

        mvn clean test -Parq-jbossas-remote 


Run the Arquillian Tests in a managed server
--------------------------------------------

_NOTE: You can also run the tests on a managed JBoss AS 7 application server. Maven will handle downloading and extracting the server for you. All you need to do is activate the Maven profile setup for running tests in this environment. _

1. Make sure you have stoped the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:

        mvn clean test -Parq-jbossas-managed 


Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](../../README.md#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts) 

Debug the Application
------------------------------------

Contributor: For example: 

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
