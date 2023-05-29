# Verifications for Selenium WebDriver

## What is this?
This is a library to enable easy verifications in Selenium. It wraps a WebDriver instance and enables verifications of browser states and element states.

## Key concepts

### Easy access of verification
You should not have to create your own verifications, so these are premade and readily available.

### Clear and consise log messsages
You should understand the log and what it says with a glance. 

### No asserts during test execution, but after.
As opposed to unit testing GUI based tests take time to execute and it is often tedious to fix small issues creating new builds and wait for them to deploy, install, and start to enable a new test execution opportunity with fixes. 
You don't want the test to stop for the slightest difference and report a lot of false negatives. Hence, in this framework verifications are not asserts, but written to the log. However, if failed verifications are found these could be asserted by simply running the following command that you may put in your JUnit @After method:
```java
driver.assertVerificationResults();
```

### Separate known issues from new ones
Any test automation that always is red is tedious to maintain. In system testing you sometimes have to live with issues for weeks before they are resolved. Hence, in this framework there is a possibility to register known issues. 
Known issues are flagged yellow rather than red or green. 
If a test exeuction only encounters known errors it is marked yellow, while it is marked red if new issues are found - and of course green if no issues are found.


## Getting started
### Maven dependency
Include the following in your pom.xml file:
```xml
<dependency>
    <groupId>com.github.claremontqualitymanagement.seleniumextensions</groupId>
    <artifactId>VerificationsForSeleniumWebDriver</artifactId>
    <version>1.1.0</version>
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
    verify().browser().titleEquals("Success").
    verify().browser().noErrorsInConsole().
    verify().element(By.id("myId")).exists();
```

#### List of implemented browser verifications
Selenium controls the browser. This enables us to verify some states of the browser.

You access the browser verifications by:
```java
driver.verify().browser()... //Enables the range of verification methods stated in the examples below:
```
noErrorsInConsole();  
alertDoesNotExist();  
alertExist();  
currentPageSourceMatchesRegex(".*mypattern.*");  
currentPageSourceContains("mySpecificString");  
currentURLContains("/importantEndpoint/");  
currentURLEquals("https://damberg.one");  
currentURLMatchesRegex(".*/api/.*/person/.*");  
titleMatchesRegex(".* - Damberg.one");  
titleContains("Damberg.one");  
titleEquals("Landing page - Damberg.one");  

#### List of implemented WebElement verifications
Selenium interacts with web pages by their elements. This enables us to verify some states of the elements.

You access the element verifications by:
```java
driver.verify().element(By.Id("myId"))... //Enables the range of verification methods stated in the examples below:
```
or by using:
```java
driver.findElement(By.Id("myId")).verify()... //Enables the range of verification methods stated in the examples below:
```


textEquals("Submit");  
textContains("Submitted");  
textMatchesRegex(".*accepted.*");  
elementAttributeExist("outbound-value");  
elementAttributeDoesNotExist("outbound-value");  
isDisplayed();  
isNotDisplayed();  
elementAttributeValueEquals("class", "outbound results");  
elementAttributeValueContains("class", "outbound");  
isDisabled();  
isEnabled();  
isSelected();  
isNotSelected();  
exists();  
doesNotExist();  
isFullyWithinViewEvenIfHidden();  
isFullyWithinView();  
isFullyOutOfView();  
isAtLeastPartlyOutOfView();
isAtLeastPartlyWithinView();

### Specific features
#### Set max number of accepted fails for a test
You may instruct the framework to abort the test if too many fails occur. This could be useful to shorten the execution duration since exceptions from expectations often trigger a lot of wait timeouts in GUI automation, making the test duration take a very long time. E.g;
```java
driver.setMaximumNumberOfAcceptedFails(3);
```
This would abort further test execution for a test if more than three failed verifications are registered.

#### Register known problems
Known issues are registered by with a regex pattern. The use of regex patterns is due to log results sometimes varying slighly from time to time. The regular expression patterns enables filtering. 
Known issues are registered like this:
```java
driver.addKnownError(".*text of element 'myElement' is .* and not 'Submitted' as expected.*");
```
Mulitple known issues may be entered:
```java
driver.addKnownErrors(
    ".*text of element 'submitButton' is .* and not 'Submit' as expected.*",
    ".*text of element 'myElement' is .* and not 'Submitted' as expected.*",
    ".*title is .* and not 'Landing page' as expected.*");
```

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
