# VerificationsForSeleniumWebDriver

## What is this?
This is a library to enable easy verifications in Selenium. It wraps a WebDriver instance and enables verifications of browser states and element states.

## Key concepts
### No asserts during test execution, but after.

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

### Compiling yourselv
Clone this repository and compile it.

### Usage
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
