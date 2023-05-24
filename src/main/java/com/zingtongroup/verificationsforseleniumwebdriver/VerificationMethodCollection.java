package com.zingtongroup.verificationsforseleniumwebdriver;

import com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.WebElementWithVerifications;
import com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.verifications.BrowserVerifications;
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

}
