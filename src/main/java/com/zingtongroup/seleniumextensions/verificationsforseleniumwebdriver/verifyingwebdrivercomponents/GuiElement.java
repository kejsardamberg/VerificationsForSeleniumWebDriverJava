package com.zingtongroup.seleniumextensions.verificationsforseleniumwebdriver.verifyingwebdrivercomponents;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface GuiElement {
    WebElement runtimeObject();
    int left();
    int right();
    int top();
    int bottom();
    List<GuiElement> childElements();
    GuiElement parent();
}