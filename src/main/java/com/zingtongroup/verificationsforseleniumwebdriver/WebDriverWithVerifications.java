package com.zingtongroup.verificationsforseleniumwebdriver;

import com.zingtongroup.loggingseleniumwebdriver.LoggingSeleniumWebDriver;
import com.zingtongroup.loggingseleniumwebdriver.logging.TestFlowLogLevel;
import com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.VerifyableWebElement;
import com.zingtongroup.verificationsforseleniumwebdriver.verifyingwebdrivercomponents.WebElementWithVerifications;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.zingtongroup.loggingseleniumwebdriver.logging.TestFlowLogLevel.*;

public class WebDriverWithVerifications extends LoggingSeleniumWebDriver {

    private TestFlowLogLevel highestSeverity;

    public WebDriverWithVerifications(WebDriver driver){
        super(driver);
        highestSeverity = TestFlowLogLevel.DEBUG;
    }

    public VerificationMethodCollection verify(){
        return new VerificationMethodCollection(this);
    }

    public void logVerificationPassed(String message){
        super.logVerificationPassed(message);
        if(highestSeverity.getValue() >= TestFlowLogLevel.PASSED_VERIFICATION.getValue()) return;
        highestSeverity = TestFlowLogLevel.PASSED_VERIFICATION;

    }

    public void logVerificationFailed(String message){
        super.logVerificationFailed(message);
        if(highestSeverity.getValue() >= TestFlowLogLevel.FAILED_VERIFICATION.getValue()) return;
        highestSeverity = TestFlowLogLevel.FAILED_VERIFICATION;
    }

    public void logVerificationProblem(String message){
        super.logVerificationProblem(message);
        if(highestSeverity.getValue() >= VERIFICATION_PROBLEM.getValue()) return;
        highestSeverity = VERIFICATION_PROBLEM;
    }

    public void assertVerificationResults(){
        Assert.isTrue(highestSeverity.getValue() < FAILED_VERIFICATION.getValue(), "Failed verifications encountered.");
        Assert.isTrue(highestSeverity.getValue() < VERIFICATION_PROBLEM.getValue(), "Could not perform all verifications.");
    }

    public VerifyableWebElement findVerifiableElement(By by){
        return new VerifyableWebElement(super.findElement(by), this);
    }

}
