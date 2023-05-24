# VerificationsForSeleniumWebDriver

## What is this?
This is a library to enable easy verifications in Selenium. It wraps a WebDriver instance and enables verifications of browser states and element states.

## Key concepts
### No asserts during test execution, but after.
As opposed to unit testing GUI based tests take time to execute and it is often tedious to fix small issues creating new builds and wait for them to deploy, install, and start to enable a new test execution opportunity with fixes. 
You don't want the test to stop for the slightest difference and report a lot of false negatives. Hence, in this framework verifications are not asserts, but written to the log. However, if failed verifications are found these could be asserted by simply running the following command that you may put in your JUnit @After method:
```java
driver.assertVerificationResults();
```

### Chained commands to enable multiple verifications


## Getting started
### Maven dependency
Include the following in your pom.xml file:
```xml
<dependency>
</dependency>
```
### Direct download
Check releases above. Download binary artifacts and include them in your classpath.

### Compiling yourself
Clone this repository and compile it.

### Usage
Just wrap the WebDriver instance with a WebDriverWithVerifications instance and you get a few more methods to work with:
```java
WebDriver driver = new WebDriverWithVerifications(new ChromeDriver());
```
This enables a verify() method that could be used to verify element states or browser states. There is also a basic text verification to enable any verification:
```java
driver.verify().browser().noErrorsInConsole();
driver.verify().element(By.id("myId")).isSelected();
driver.verify().textContains(driver.getPageSource(), "Test purpose");
```
These commands enable chaining since they return the verification driver:
```java
driver.
    verify().element(By.id("myId")).exists().
    verify().element(By.id("myId"))
```

#### List of browser verifications
driver.verify().browser().noErrorsInConsole();
driver.verify().browser().noErrorsInConsole();

#### List of 

### Working code sample
```java
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {

    WebDriverWithVerifications driver;

    @Before
    public void setup(){
        driver = new WebDriverWithVerifications(new ChromeDriver());
    }

    @After
    public void teardown(){
        if(driver != null){
            driver.assertVerificationResults();
            driver.quit();
        }
    }

    By searchSection = By.id("search-section");

    @Test
    public void constructor(){
        driver.get("https://damberg.one");
        driver.verify().browser().titleContains("Damberg.one");
        driver.verify().element(searchSection).textEquals("Problem");
        driver.findVerifiableElement(searchSection).verify().isEnabled();
    }
}
```
