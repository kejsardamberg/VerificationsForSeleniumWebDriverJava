package com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents;

import com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents.LoggingWebElement;
import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerifyableWebElement extends LoggingWebElement {

    WebDriverWithVerifications driver;

    public VerifyableWebElement(WebElement webElement, WebDriverWithVerifications driver) {
        super(webElement, driver.loggerList);
        this.driver = driver;
    }

    public VerifyableWebElement(By by, WebDriverWithVerifications driver){
        super(driver.findElement(by), driver.loggerList);
        this.driver = driver;
    }

    public WebElementWithVerifications verify(){
        return new WebElementWithVerifications(this, driver);
    }
}
