package com.zingtongroup.verificationsforseleniumwebdriver;

import com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.WebDriverWithVerifications;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    @Ignore
    public void constructor(){
        driver.get("https://damberg.one");
        driver.verify().browser().titleContains("Damberg.one");
//        driver.verify().element(searchSection).textEquals("Problem");
        driver.findElement(searchSection).verify().isEnabled();
        driver.findElement(searchSection).verify().isAtLeastPartlyWithinView();
    }
}
