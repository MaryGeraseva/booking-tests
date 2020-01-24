# Booking tests

## Tools
Java(1.8), Maven, Selenium WebDriver, Selenium Grid, TestNG, Log4j, Allure

## Features
I paid special attention to logging and reporting.  
The project uses a thread-safe singleton logger. The logger makes a personal log file for each test method run “on the fly” and attaches it to the Allure report. It’s truly convenient for bugs localization and makes report clear and pretty.  
There is also used Allure reporting features for showing the most important test steps. The framework makes a screenshot on each test failure and shows it within the Allure report.

## Usage
### Requirements
Be sure to check for drivers and system environment variables:  
* JAVA_HOME 
* ALLURE_HOME
* M2_HOME (Maven)
* webdriver.chrome.driver
* webdriver.gecko.driver

### How to run the tests suite  

By default suite configured to run in 2 threads in parallel. 

* run testNG suite by default *(on Chrome locally)*   
`
mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\allTestsSuite.xml
`  
* or run on Firefox  
`
mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\allTestsSuite.xml -Dbrowser=firefox
`  

To run on grid make sure that at least 2 nodes of Chrome and/or Firefox are up and running in hub.

* in Grid on Chrome run  
`
mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\allTestsSuite.xml -Dhub.url={$url}
` 

* in Grid on Firefox run    
`
mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\allTestsSuite.xml -Dbrowser=firefox -Dhub.url={$url}
` 

### Logging and reporting

* generate allure report `mvn allure:serve`

Сan find the logs in the folder `.\target\logs\methods`
or in attach to the allure report in section  `Tear down >> tearDown >> Logs`

## For feedback
**e-mail:** mary.geraseva@gmail.com  
**telegram:** @MaryGeraseva  
**skype:** mary_geraseva  
[linkedIn](https://www.linkedin.com/in/maria-geraseva/)
