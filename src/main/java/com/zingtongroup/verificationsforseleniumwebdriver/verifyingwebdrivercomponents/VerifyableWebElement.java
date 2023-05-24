package com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents;

import com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents.LoggingWebElement;
import com.zingtongroup.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.openqa.selenium.WebElement;

public class VerifyableWebElement extends LoggingWebElement {

    WebDriverWithVerifications driver;

    public VerifyableWebElement(WebElement webElement, WebDriverWithVerifications driver) {
        super(webElement, driver.loggerList);
        this.driver = driver;
    }

    public WebElementWithVerifications verify(){
        return new WebElementWithVerifications(this, driver);
    }
}
