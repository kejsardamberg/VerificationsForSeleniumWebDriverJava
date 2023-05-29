package com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.verifications;

import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class BrowserVerifications {

    WebDriverWithVerifications driver;

    public BrowserVerifications(WebDriverWithVerifications driver){
        this.driver = driver;
    }

    public WebDriverWithVerifications titleEquals(String title){
        if(driver == null) driver.logVerificationProblem("Could not verify browser title.");
        driver.pauseLogging();
        String actualTitle = driver.getTitle();
        driver.resumeLogging();
        if(actualTitle == null){
            driver.logVerificationProblem("Could not verify browser title. Title was null.");
            return driver;
        }
        if(actualTitle.equals(title)){
            driver.logVerificationPassed("Browser title was '" + title + "' as expected.");
        } else {
            driver.logVerificationFailed("Browser title was '" + actualTitle + "' but was expected to be '" + title + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications titleContains(String titleMatch){
        if(driver == null) driver.logVerificationProblem("Could not verify browser title.");
        driver.pauseLogging();
        String actualTitle = driver.getTitle();
        driver.resumeLogging();
        if(actualTitle == null){
            driver.logVerificationProblem("Could not verify browser title. Title was null.");
            return driver;
        }
        if(actualTitle.contains(titleMatch)){
            driver.logVerificationPassed("Browser title was '" + actualTitle + "' thus containing '" + titleMatch + "' as expected.");
        } else {
            driver.logVerificationFailed("Browser title was '" + actualTitle + "' but was expected to contain '" + titleMatch + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications titleMatchesRegex(String titlePattern){
        if(driver == null) driver.logVerificationProblem("Could not verify browser title.");
        driver.pauseLogging();
        String actualTitle = driver.getTitle();
        driver.resumeLogging();
        if(actualTitle == null){
            driver.logVerificationProblem("Could not verify browser title. Title was null.");
            return driver;
        }
        if(actualTitle.matches(titlePattern)){
            driver.logVerificationPassed("Browser title was '" + actualTitle + "' thus matching '" + titlePattern + "' as expected.");
        } else {
            driver.logVerificationFailed("Browser title was '" + actualTitle + "', not matching '" + titlePattern + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications currentURLMatchesRegex(String urlPattern){
        if(driver == null) driver.logVerificationProblem("Could not verify browser URL.");
        driver.pauseLogging();
        String actualUrl = driver.getCurrentUrl();
        driver.resumeLogging();
        if(actualUrl == null){
            driver.logVerificationProblem("Could not verify current browser URL. URL was null.");
            return driver;
        }
        if(actualUrl.matches(urlPattern)){
            driver.logVerificationPassed("Current browser URL was '" + actualUrl + "' thus matching '" + urlPattern + "' as expected.");
        } else {
            driver.logVerificationFailed("Current browser URL was '" + actualUrl + "', not matching '" + urlPattern + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications currentURLEquals(String url){
        if(driver == null) driver.logVerificationProblem("Could not verify browser URL.");
        driver.pauseLogging();
        String actualUrl = driver.getCurrentUrl();
        driver.resumeLogging();
        if(actualUrl == null){
            driver.logVerificationProblem("Could not verify current browser URL. URL was null.");
            return driver;
        }
        if(actualUrl.equals(url)){
            driver.logVerificationPassed("Current browser URL was '" + actualUrl + "' as expected.");
        } else {
            driver.logVerificationFailed("Current browser URL was '" + actualUrl + "', not matching '" + url + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications currentURLContains(String urlPart){
        if(driver == null) driver.logVerificationProblem("Could not verify browser URL.");
        driver.pauseLogging();
        String actualUrl = driver.getCurrentUrl();
        driver.resumeLogging();
        if(actualUrl == null){
            driver.logVerificationProblem("Could not verify current browser URL. URL was null.");
            return driver;
        }
        if(actualUrl.contains(urlPart)){
            driver.logVerificationPassed("Current browser URL was '" + actualUrl + "' thus containing '" + urlPart + "' as expected.");
        } else {
            driver.logVerificationFailed("Current browser URL was '" + actualUrl + "', not containing '" + urlPart + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications currentPageSourceContains(String stringToFind){
        if(driver == null) driver.logVerificationProblem("Could not verify current page source.");
        driver.pauseLogging();
        String actualPageSource = driver.getPageSource();
        driver.resumeLogging();
        if(actualPageSource == null){
            driver.logVerificationProblem("Could not verify current page source. It was null.");
            return driver;
        }
        if(actualPageSource.contains(stringToFind)){
            driver.logVerificationPassed("Current page source contained '" + stringToFind + "' as expected.");
        } else {
            driver.logVerificationFailed("Current page source did not contain '" + stringToFind + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications currentPageSourceMatchesRegex(String patternToFind){
        if(driver == null) driver.logVerificationProblem("Could not verify current page source.");
        driver.pauseLogging();
        String actualPageSource = driver.getPageSource();
        driver.resumeLogging();
        if(actualPageSource == null){
            driver.logVerificationProblem("Could not verify current page source. It was null.");
            return driver;
        }
        if(actualPageSource.matches(patternToFind)){
            driver.logVerificationPassed("Current page source matched '" + patternToFind + "' as expected.");
        } else {
            driver.logVerificationFailed("Current page source did not match '" + patternToFind + "'.");
        }
        return driver;
    }

    public WebDriverWithVerifications alertExist(){
        try{
            driver.switchTo().alert();
            driver.logVerificationPassed("Alert was found as expected.");
        }catch (Exception e){
            driver.logVerificationFailed("Alert was not present, but was expected to be.");
        }
        return driver;
    }

    public WebDriverWithVerifications alertDoesNotExist(){
        try{
            driver.switchTo().alert();
            driver.logVerificationFailed("Alert was present, but was expected not to be.");
        }catch (Exception e){
            driver.logVerificationPassed("Alert was not found, as expected.");
        }
        return driver;
    }

    /**
     * Note: This method empties the logs when executed.
     * @return The WebDriverWithVerifications instance for chained commands.
     */
    public WebDriverWithVerifications noErrorsInConsole(){
        driver.pauseLogging();
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        List<String> severeLogMessages = new ArrayList<>();
        for(String logType : logTypes){
            LogEntries entries = driver.manage().logs().get(logType);
            for(LogEntry entry : entries){
                if(entry.getLevel() == Level.SEVERE){
                    severeLogMessages.add(logType + ": " + entry.getMessage());
                }
            }
        }
        if(severeLogMessages.size() > 0){
            driver.logVerificationFailed("Browser has the following log entries of at least log level 'severe' in console:" + System.lineSeparator() + String.join(System.lineSeparator(), severeLogMessages));
        } else {
            driver.logVerificationPassed("Browser had no severe log entries in console.");
        }
        return driver;
    }

}
