package com.zingtongroup.verificationsforseleniumwebdriver;

import com.zingtongroup.loggingseleniumwebdriver.LoggingSeleniumWebDriver;
import com.zingtongroup.loggingseleniumwebdriver.logging.TestFlowLogLevel;
import com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.VerifyableWebElement;
import dev.failsafe.internal.util.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zingtongroup.loggingseleniumwebdriver.logging.TestFlowLogLevel.*;

public class WebDriverWithVerifications extends LoggingSeleniumWebDriver {

    private TestFlowLogLevel highestSeverity;
    private int numberOfProblems;
    private int maximumNumberOfAcceptedFails;
    private final List<String> knownFailRegexStrings;
    private int knownErrorsEncountered;

    public WebDriverWithVerifications(WebDriver driver){
        super(driver);
        numberOfProblems = 0;
        maximumNumberOfAcceptedFails = 1000;
        highestSeverity = TestFlowLogLevel.DEBUG;
        knownFailRegexStrings = new ArrayList<>();
        knownErrorsEncountered = 0;
    }

    public VerificationMethodCollection verify(){
        return new VerificationMethodCollection(this);
    }

    public VerifyableWebElement verifyElement(By by){
        return new VerifyableWebElement(by, this);
    }

    public void addKnownError(String errorMessageAsRegex){
        knownFailRegexStrings.add(errorMessageAsRegex);
    }

    public void addKnownErrors(String... errorMessagesAsRegex){
        knownFailRegexStrings.addAll(Arrays.asList(errorMessagesAsRegex));
    }

    public void setMaximumNumberOfAcceptedFails(int maximumNumberOfAcceptedFails){
        this.maximumNumberOfAcceptedFails = maximumNumberOfAcceptedFails;
    }

    public void logVerificationPassed(String message){
        super.logVerificationPassed(message);
        if(highestSeverity.getValue() >= TestFlowLogLevel.PASSED_VERIFICATION.getValue()) return;
        highestSeverity = TestFlowLogLevel.PASSED_VERIFICATION;

    }

    public void logVerificationFailed(String message){
        super.logVerificationFailed(message);
        for(String errorMessage : knownFailRegexStrings){
            if(message.matches(errorMessage)){
                knownErrorsEncountered++;
            } else {
                numberOfProblems++;
            }
        }
        if(highestSeverity.getValue() < TestFlowLogLevel.FAILED_VERIFICATION.getValue()) {
            highestSeverity = TestFlowLogLevel.FAILED_VERIFICATION;
        }
        if(numberOfProblems >= maximumNumberOfAcceptedFails){
            logInfo("Maximum number of accepted failed log posts (" + maximumNumberOfAcceptedFails + ") reached. Aborting test execution.");
            quit();
        }
    }

    public void logVerificationProblem(String message){
        super.logVerificationProblem(message);
        for(String errorMessage : knownFailRegexStrings){
            if(message.matches(errorMessage)){
                knownErrorsEncountered++;
            } else {
                numberOfProblems++;
            }
        }
        if(highestSeverity.getValue() < VERIFICATION_PROBLEM.getValue()) {
            highestSeverity = VERIFICATION_PROBLEM;
        }
        if(numberOfProblems >= maximumNumberOfAcceptedFails){
            logInfo("Maximum number of accepted failed log posts (" + maximumNumberOfAcceptedFails + ") reached. Aborting test execution.");
            quit();
        }
    }

    public void assertVerificationResults(){
        Assert.isTrue(highestSeverity.getValue() < FAILED_VERIFICATION.getValue()  && numberOfProblems == 0, "Failed verifications encountered.");
        Assert.isTrue(highestSeverity.getValue() < VERIFICATION_PROBLEM.getValue() && numberOfProblems == 0, "Could not perform all verifications.");
        Assume.assumeTrue("Only deviations marked as known encountered.", knownErrorsEncountered == 0);
    }

    @Override
    public VerifyableWebElement findElement(By by){
        return new VerifyableWebElement(super.findElement(by), this);
    }

}
