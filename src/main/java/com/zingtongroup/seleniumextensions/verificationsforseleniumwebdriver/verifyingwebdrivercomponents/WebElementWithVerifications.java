package com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents;

import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggingseleniumcomponents.LoggingWebElement;
import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class WebElementWithVerifications implements GuiElement {

    private final WebDriverWithVerifications driver;
    private final LoggingWebElement element;

    public WebElementWithVerifications(WebElement webElement, WebDriverWithVerifications driver) {
        this.element = new LoggingWebElement(webElement, driver.loggerList);
        this.driver = driver;
    }

    public WebDriverWithVerifications verifyTextEquals(String text){
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

    public WebDriverWithVerifications verifyTextContains(String text){
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

    public WebDriverWithVerifications verifyTextMatchesRegex(String pattern){
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

    public WebDriverWithVerifications verifyElementAttributeExist(String attributeName){
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

    public WebDriverWithVerifications verifyElementAttributeDoesNotExist(String attributeName){
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

    public WebDriverWithVerifications verifyIsDisplayed(){
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

    public WebDriverWithVerifications verifyIsNotDisplayed(){
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

    public WebDriverWithVerifications verifyElementAttributeValueEquals(String attributeName, String attributeValue){
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

    public WebDriverWithVerifications verifyElementAttributeValueContains(String attributeName, String attributeValue){
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

    public WebDriverWithVerifications verifyIsDisabled(){
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

    public WebDriverWithVerifications verifyIsEnabled(){
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

    public WebDriverWithVerifications verifyIsSelected(){
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

    public WebDriverWithVerifications verifyIsNotSelected(){
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

    public WebDriverWithVerifications verifyExists(){
        if(element != null){
            driver.logVerificationPassed("Element '" + element.elementString + "' existed as expected.");
        } else {
            driver.logVerificationFailed("Element did not exist as expected.");
        }
        return driver;
    }

    public WebDriverWithVerifications verifyDoesNotExist(){
        if(element == null){
            driver.logVerificationPassed("Element did not exist, as expected.");
        } else {
            driver.logVerificationFailed("Element '" + element.elementString + "' did exist while it was expected not to.");
        }
        return driver;
    }

    private boolean withinViewPort(boolean partialDisplayAccepted, boolean acceptHiddenElements)
    {
        JavascriptExecutor js;
        try{
            js = (JavascriptExecutor)driver.originalWebDriver;
        }catch (Exception e){
            driver.logVerificationProblem("Cannot execute javascripts with this WebDriver instance.");
            return false;
        }
        driver.pauseLogging();
        if (!acceptHiddenElements && !element.isDisplayed()) return false;
        boolean success;
        try
        {
            Integer viewPortTop = Integer.valueOf(js.executeScript("return window.pageYOffset;").toString());
            Integer viewPortLeft = Integer.valueOf(js.executeScript("return window.pageXOffset;").toString());
            Integer viewPortWidth = Integer.valueOf(js.executeScript("return Math.max(document.documentElement.clientWidth, window.innerWidth || 0);").toString());
            Integer viewPortHeight = Integer.valueOf(js.executeScript("return Math.max(document.documentElement.clientHeight, window.innerHeight || 0);").toString());

            success = !(partialDisplayAccepted && viewPortTop > this.bottom());
            if (!partialDisplayAccepted && viewPortTop > top()) success = false;

            if (partialDisplayAccepted && viewPortLeft > right()) success = false;
            if (!partialDisplayAccepted && viewPortLeft > left()) success = false;

            if (partialDisplayAccepted && viewPortLeft + viewPortWidth < left()) success = false;
            if (!partialDisplayAccepted && viewPortLeft + viewPortWidth < right()) success = false;

            if (partialDisplayAccepted && viewPortTop + viewPortHeight < bottom()) success = false;
            if (!partialDisplayAccepted && viewPortTop + viewPortHeight < top()) success = false;
        }
        catch (Exception e)
        {
            driver.resumeLogging();
            return false;
        }
        driver.resumeLogging();
        return success;
    }

    public WebDriverWithVerifications verifyIsFullyWithinViewEvenIfHidden()
    {
        return isWithinViewBase(false, true, true);
    }

    public WebDriverWithVerifications verifyIsFullyWithinView()
    {
        return isWithinViewBase(false, true, false);
    }

    public WebDriverWithVerifications verifyIsFullyOutOfView()
    {
        return isWithinViewBase(false, false, false);
    }

    public WebDriverWithVerifications verifyIsAtLeastPartlyOutOfView()
    {
        return isWithinViewBase(true, false, false);
    }

    public WebDriverWithVerifications verifyIsAtLeastPartlyWithinView()
    {
        return isWithinViewBase(true, true, false);
    }

    private WebDriverWithVerifications isWithinViewBase(boolean partialDisplayAccepted, boolean expectedToBeWithinViewForSuccess, boolean acceptHiddenElements)
    {
        boolean success = withinViewPort(partialDisplayAccepted, acceptHiddenElements) == expectedToBeWithinViewForSuccess;

        if (success)
        {
            driver.logVerificationPassed("Element '" + this.element.elementString + "' was " + String.valueOf(expectedToBeWithinViewForSuccess).toLowerCase().replace("false", "not ").replace("true", "") + "within view, as expected.");
        }
        else
        {
            driver.logVerificationFailed("Element '" + element.elementString + "' was unexpectedly " + String.valueOf(expectedToBeWithinViewForSuccess).toLowerCase().replace("false", "").replace("true", "not ") + "within view.");
        }

        return this.driver;
    }

    public WebDriverWithVerifications verifyIsToTheRightOf(By referenceElement)
    {
        if (referenceElement == null )
        {
            driver.logVerificationProblem("Could not verify element position of null elements.");
            return this.driver;
        }

        VerifyableWebElement leftElement = driver.findElement(referenceElement);
        if(leftElement == null){
            driver.logVerificationProblem("Could not verify element position since element could not be identified.");
            return this.driver;
        }
        Point leftElementLocation = leftElement.getLocation();
        Point rightElementLocation = element.getLocation();

        if (leftElementLocation == null || rightElementLocation == null)
        {
            driver.logVerificationProblem("Could not verify element position of elements '" + element.elementString + "' and '" + referenceElement + "'. Some elements could not be identified.");
            return this.driver;
        }
        int leftElementWidth = leftElement.getRect().width;

        if (leftElementLocation.getX() + leftElementWidth < rightElementLocation.getX())
        {
            driver.logVerificationPassed("Verified that element '" + element.elementString + "' was to the right of element '" + referenceElement + "'.");
        }
        else
        {
            driver.logVerificationFailed("The element '" + element.elementString + "' was not to the right of element '" + referenceElement + "'.");
        }
        return this.driver;
    }

    public WebDriverWithVerifications verifyIsToTheLeftOf(By referenceElement)
    {
        if (referenceElement == null )
        {
            driver.logVerificationProblem("Could not verify element position of null elements.");
            return this.driver;
        }

        VerifyableWebElement rightElement = driver.findElement(referenceElement);
        if(rightElement == null){
            driver.logVerificationProblem("Could not verify element position since element could not be identified.");
            return this.driver;
        }
        Point rightElementLocation = rightElement.getLocation();
        Point leftElementLocation = element.getLocation();

        if (rightElementLocation == null || leftElementLocation == null)
        {
            driver.logVerificationProblem("Could not verify element position of elements '" + element.elementString + "' and '" + referenceElement + "'. Some elements could not be identified.");
            return this.driver;
        }
        int rightElementWidth = rightElement.getRect().width;

        if (rightElementLocation.getX() > leftElementLocation.getX() + element.getRect().getWidth())
        {
            driver.logVerificationPassed("Verified that element '" + element.elementString + "' was to the left of element '" + referenceElement + "'.");
        }
        else
        {
            driver.logVerificationFailed("The element '" + element.elementString + "' was not to the left of element '" + referenceElement + "'.");
        }
        return this.driver;
    }

    @Override
    public int left() {
        return element.getLocation().getX();
    }

    @Override
    public int right() {
        return element.getLocation().getX() + element.getRect().getWidth();
    }

    @Override
    public int top() {
        return element.getLocation().getY();
    }

    @Override
    public int bottom() {
        return element.getLocation().getY() + element.getRect().getHeight();
    }
}
