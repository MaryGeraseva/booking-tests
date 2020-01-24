# Booking tests

## Tools
Java(1.8), Maven, Selenium WebDriver, Selenium Grid, TestNG, Log4j, Allure

## Features
I paid special attention to logging and reporting.  
The project uses a thread-safe singleton logger. The logger makes a personal log file for each test method run “on the fly” and attaches it to the Allure report. It’s truly convenient for bugs localization and makes report clear and pretty.  
There is also used Allure reporting features for showing the most important test steps. The framework makes a screenshot on each test failure and shows it within the Allure report.

## Usage
### How to run the tests suite
Be sure to check for drivers and system variables:  
* JAVA_HOME 
* ALLURE_HOME
* webdriver.chrome.driver
* webdriver.gecko.driver

### How to run the tests suite  

* run testNG suite by default *(on chrome locally)*   
`
mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\allTestsSuite.xml
`  
or add parameters:
  * run on Selenium grid server `-Dselenium.server=grid -Dserver.url=[$url]}`
  * run on chrome `-Dselenium.browser=chrome`
  * run on firefox `-Dselenium.browser=firefox`
 
* generate allure report `mvn allure:serve`

### Logging and reporting
Сan find the logs in the folder `.\target\logs\methods`
or in attach to the allure report  `Tear down >> tearDown >> Logs`

## For feedback
**e-mail:** mary.geraseva@gmail.com  
**telegram:** @MaryGeraseva  
**skype:** mary_geraseva  
[linkedIn](https://www.linkedin.com/in/maria-geraseva/)
