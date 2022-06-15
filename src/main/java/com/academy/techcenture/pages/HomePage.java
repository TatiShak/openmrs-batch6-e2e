package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    public WebDriver driver;
    public SoftAssert softAssert;

    public HomePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//li[contains(@class,'nav-item identifier')]")
    private WebElement adminLoginBtn;

    @FindBy(id = "selected-location")
    private WebElement selectedLocationBtn;

    @FindBy(linkText = "Logout")
    private WebElement logOut;

    @FindBy(xpath = "//h4[contains(text(),'  Logged in as Super User')]")
    private WebElement loginAsHeader;

    @FindBy(xpath = "//a[contains(@id,'referenceapplication')][1]")
    private WebElement registerAPatientBtn;

    @FindBy(xpath = "//div[@id='apps']/a")
    private List<WebElement> tabs;
    String[] tabsArray = {"Find Patient Record", "Active Visits","Register a patient", "Capture Vitals","Appointment Scheduling",
            "Reports", "Data Management", "Configure Metadata", "System Administration"};


    private void verifyHomePageTabs() {
        List<String> collect = tabs.stream().map(t -> t.getText().trim()).collect(Collectors.toList()); //10 11
        for (int i = 0; i < tabsArray.length; i++) {
            softAssert.assertTrue(collect.contains(tabsArray[i]),"Home Page Tabs was changed again");
        }
    }
    private void verifyingHomePage() {
        softAssert.assertEquals(driver.getTitle(), "Home");
        softAssert.assertEquals(adminLoginBtn.getText().trim(), "admin");
        softAssert.assertTrue(selectedLocationBtn.isDisplayed());

        softAssert.assertTrue(logOut.isDisplayed());


    }

    public void clickOnRegisterPatientTab(){
        verifyHomePageTabs();
        verifyingHomePage();
        softAssert.assertTrue(registerAPatientBtn.isEnabled());
        registerAPatientBtn.click();
    }



}






















