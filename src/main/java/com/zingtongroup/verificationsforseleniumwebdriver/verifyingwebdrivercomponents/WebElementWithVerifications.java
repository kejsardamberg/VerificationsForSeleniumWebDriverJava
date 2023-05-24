package com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents;

import com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents.LoggingWebElement;
import com.zingtongroup.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;

public class WebElementWithVerifications {

    private final WebDriverWithVerifications driver;
    private final LoggingWebElement element;

    public WebElementWithVerifications(WebElement webElement, WebDriverWithVerifications driver) {
        this.element = new LoggingWebElement(webElement, driver.loggerList);
        this.driver = driver;
    }

    public WebDriverWithVerifications textEquals(String text){
        if(text == null) {
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". Expected text was null.");
            return driver;
        }
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        String actualText = element.getText();
        driver.resumeLogging();
        if(actualText == null){
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". It was null.");
            return driver;
        }
        if(actualText.equals(text)){
            driver.logVerificationPassed("Element text was '" + text + "' as expected.");
        }else {
            driver.logVerificationFailed("Element text was '" +actualText + "', not the expected '" + text + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications textContains(String text){
        if(text == null) {
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". Expected text was null.");
            return driver;
        }
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }

        driver.pauseLogging();
        String actualText = element.getText();
        driver.resumeLogging();
        if(actualText == null){
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". It was null.");
            return driver;
        }
        if(actualText.contains(text)){
            driver.logVerificationPassed("Element text was '" + text + "' which contain '" + text + "' as expected.");
        }else {
            driver.logVerificationFailed("Element text was '" +actualText + "', not containing the expected '" + text + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications textMatchesRegex(String pattern){
        if(pattern == null) {
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". Expected pattern was null.");
            return driver;
        }
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }

        driver.pauseLogging();
        String actualText = element.getText();
        driver.resumeLogging();
        if(actualText == null){
            driver.logVerificationProblem("Could not verify text of element " + element.elementString + ". It was null.");
            return driver;
        }
        if(actualText.matches(pattern)){
            driver.logVerificationPassed("Element text was '" + actualText + "', matching the pattern '" + pattern + "' as expected.");
        }else {
            driver.logVerificationFailed("Element text was '" +actualText + "', not matching the pattern '" + pattern + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications elementAttributeExist(String attributeName){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        String attrib = element.getAttribute(attributeName);
        driver.resumeLogging();
        if(attrib != null){
            driver.logVerificationPassed("Element '" + element.elementString + "' had the attribute '" + attributeName + "' as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' did not have the expected attribute '" + attributeName + "'.");
        }

        return driver;
    }

    public WebDriverWithVerifications elementAttributeDoesNotExist(String attributeName){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        String attrib = element.getAttribute(attributeName);
        driver.resumeLogging();
        if(attrib == null ){
            driver.logVerificationPassed("Element '" + element.elementString + "' did not have the attribute '" + attributeName + "', as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' did have the expected attribute '" + attributeName + "'. It was expected not to have this.");
        }

        return driver;
    }

    public WebDriverWithVerifications isDisplayed(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean displayed = element.isDisplayed();
        driver.resumeLogging();
        if(displayed){
            driver.logVerificationPassed("Element '" + element.elementString + "' was displayed, as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' was expected to be displayed, but it was not.");
        }

        return driver;

    }

    public WebDriverWithVerifications isNotDisplayed(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean displayed = element.isDisplayed();
        driver.resumeLogging();
        if(displayed){
            driver.logVerificationFailed("Element '" + element.elementString + "' was displayed while expected not to be.");
        } else {
            driver.logVerificationPassed("Element '" + element.elementString + "' was not displayed, as expected.");
        }

        return driver;
    }

    public WebDriverWithVerifications elementAttributeValueEquals(String attributeName, String attributeValue){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        String value = element.getAttribute(attributeName);
        driver.resumeLogging();
        if(value == null){
            driver.logVerificationProblem("Could not read value of attribute '" + attributeName + "' since it could not be found.");
            return driver;
        }
        if(value.equals(attributeValue)){
            driver.logVerificationPassed("Element '" + element.elementString + "' had the value '" + attributeValue + "' for attribute '" + attributeName + "'.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' had the value '" + value + "' for attribute '" + attributeName + "', not '" + attributeValue + "' as expected.");

        }

        return driver;
    }

    public WebDriverWithVerifications elementAttributeValueContains(String attributeName, String attributeValue){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        String value = element.getAttribute(attributeName);
        driver.resumeLogging();
        if(value == null){
            driver.logVerificationProblem("Could not read value of attribute '" + attributeName + "' since it could not be found.");
            return driver;
        }
        if(value.contains(attributeValue)){
            driver.logVerificationPassed("Element '" + element.elementString + "' had the value '" + value + "' for attribute '" + attributeName + "', containing '" + attributeValue + "'.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' had the value '" + value + "' for attribute '" + attributeName + "', not containing '" + attributeValue + "' as expected.");
        }
        return driver;
    }

    public WebDriverWithVerifications isDisabled(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean enabled = element.isEnabled();
        driver.resumeLogging();
        if(enabled){
            driver.logVerificationFailed("Element '" + element.elementString + "' was expected to be disabled, but it was.");
        } else {
            driver.logVerificationPassed("Element '" + element.elementString + "' was disabled, as expected.");
        }

        return driver;
    }

    public WebDriverWithVerifications isEnabled(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean enabled = element.isEnabled();
        driver.resumeLogging();
        if(enabled){
            driver.logVerificationPassed("Element '" + element.elementString + "' was enabled, as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' was expected to be enabled, but it was not.");
        }

        return driver;
    }

    public WebDriverWithVerifications isSelected(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean selected = element.isSelected();
        driver.resumeLogging();
        if(selected){
            driver.logVerificationPassed("Element '" + element.elementString + "' was selected, as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' was expected to be selected, but it was not.");
        }

        return driver;
    }

    public WebDriverWithVerifications isNotSelected(){
        if(element == null){
            driver.logVerificationProblem("Could not verify text of element '" + element.elementString + "' since it could not be identified.");
            return driver;
        }
        driver.pauseLogging();
        boolean selected = element.isSelected();
        driver.resumeLogging();
        if(selected){
            driver.logVerificationFailed("Element '" + element.elementString + "' was expected to not be selected, but it was.");
        } else {
            driver.logVerificationPassed("Element '" + element.elementString + "' was not selected, as expected.");
        }

        return driver;
    }

    public WebDriverWithVerifications exists(){
        if(element != null){
            driver.logVerificationPassed("Element '" + element.elementString + "' existed as expected.");
        } else {
            driver.logVerificationFailed("Element did not exist as expected.");
        }
        return driver;
    }

    public WebDriverWithVerifications doesNotExist(){
        if(element == null){
            driver.logVerificationPassed("Element did not exist, as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' did exist while it was expected not to.");
        }
        return driver;
    }


}
