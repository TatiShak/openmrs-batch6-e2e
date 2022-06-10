package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class PatientDetailsPage {
    private WebDriver driver;
    private SoftAssert softAssert;

    public PatientDetailsPage(WebDriver driver, SoftAssert softAssert){
        this.driver=driver;
        this.softAssert=softAssert;
        PageFactory.initElements(driver,this);
    }





}
