package com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver;

import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.WebElementWithVerifications;
import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.verifications.BrowserVerifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerificationMethodCollection {

    WebDriverWithVerifications driver;

    public VerificationMethodCollection(WebDriverWithVerifications driver) {
        this.driver = driver;
    }

    public BrowserVerifications browser(){
        return new BrowserVerifications(driver);
    }

    public WebElementWithVerifications element(WebElement element){
        return new WebElementWithVerifications(element, driver);
    }

    public WebElementWithVerifications element(By by){
        return new WebElementWithVerifications(driver.findElement(by), driver);
    }

    public WebDriverWithVerifications textEquals(String text1, String text2){
        if(String.valueOf(text1).equals(String.valueOf(text2))){
            driver.logVerificationPassed("Text check passed for equality test of '" + String.valueOf(text1) + "'.");
        }else {
            driver.logVerificationProblem("Text '" + String.valueOf(text1) + "' was not equal to '" + String.valueOf(text2) + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications textContains(String fullString, String part){
        if((fullString == null && part == null) || (fullString != null &&  part != null && fullString.contains(part))){
            driver.logVerificationPassed("The string '" + String.valueOf(part) + "' found in '" + String.valueOf(fullString) + "'.");
        } else {
            driver.logVerificationFailed("The string '" + String.valueOf(part) + "' not found in '" + String.valueOf(fullString) + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications isTrue(boolean expression){
        if(expression){
            driver.logVerificationPassed("Expression evaluated to true, as expected.");
        } else {
            driver.logVerificationFailed("Expression evaluated to false. True was expected.");
        }
        return this.driver;
    }

    public WebDriverWithVerifications isTrue(boolean expression, String message){
        if(expression){
            driver.logVerificationPassed(message);
        } else {
            driver.logVerificationFailed(message);
        }
        return this.driver;
    }

    public WebDriverWithVerifications isTrue(boolean expression, String messageIfTrue, String messageIfFalse){
        if(expression){
            driver.logVerificationPassed(messageIfTrue);
        } else {
            driver.logVerificationFailed(messageIfFalse);
        }
        return this.driver;
    }

    public WebDriverWithVerifications isFalse(boolean expression, String messageIfFalse, String messageIfTrue){
        if(expression){
            driver.logVerificationFailed(messageIfTrue);
        } else {
            driver.logVerificationPassed(messageIfFalse);
        }
        return this.driver;
    }

    public WebDriverWithVerifications isFalse(boolean expression){
        if(expression){
            driver.logVerificationFailed("Expression evaluated to false, as expected.");
        } else {
            driver.logVerificationPassed("Expression evaluated to true. False was expected.");
        }
        return this.driver;
    }

    public WebDriverWithVerifications isFalse(boolean expression, String message){
        if(!expression){
            driver.logVerificationPassed(message);
        } else {
            driver.logVerificationFailed(message);
        }
        return this.driver;
    }

    public WebDriverWithVerifications equals(Object obj1, Object obj2){
        if((obj1 == null && obj2 == null) || obj1 != null && obj1.equals(obj2)){
            driver.logVerificationPassed("The objects were equal.");
        } else {
            driver.logVerificationFailed("The compared objects were not equal. Object 1: " + String.valueOf(obj1) + ", Object 2: " + String.valueOf(obj2) + ".");
        }
        return this.driver;
    }

}
