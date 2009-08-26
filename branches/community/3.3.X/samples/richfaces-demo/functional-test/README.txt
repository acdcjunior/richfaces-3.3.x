1) Build environment

Needs third-party library selenium-testing-lib library.

You can obtain it from https://svn.devel.redhat.com/repos/jboss-qa/wfk/trunk/selenium-testing-lib
(until it is available from maven repositories).

Install it into your local maven repository.


2) Running tests

It's necessary to have running web/app server with richfaces-demo deployed on it.

To run tests, you have to set up Maven's property called "browser" which contains browser
type and path to its binary if needed. You can specify application's context by setting Maven properties 
named "context.root" and "context.path". 

To set them, either edit profiles.xml or pass runtime arguments to maven, e.g.
    mvn install -Dbrowser=*firefox
    mvn install -Dbrowser="*firefox /usr/lib/firefox-3.5.2/firefox"
    mvn install -Dbrowser=*iexplore -Dcontext.root=http://localhost:8080/ -Dcontext.path=/richfaces-demo

Default values are set in pom.xml.


3) Profiles

Since some tests might not be runnable on some platforms, there are separate testng.xml files in
src/test/profiles for each platform (unix/windows/mac).


4) Properties files

The majority of locators and assert values used in test are externalized in *.properties files. You can find 
all string belonging to the class in src/test/resources/packagename/locators.properties or 
src/test/resources/packagename/messages.properties.


5) Generating testng.xml

$ find -name "*.java" | grep -v Abstract  | sort | tr '/' '.' | sed -r 's#^..(.*).java$#<test name="\1">\n\t<classes>\n\t\t<class name="\1" />\n\t</classes>\n</test>\n#' 




