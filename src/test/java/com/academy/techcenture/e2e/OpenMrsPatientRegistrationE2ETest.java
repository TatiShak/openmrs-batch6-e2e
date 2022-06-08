package com.academy.techcenture.e2e;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.config.Driver;
import com.academy.techcenture.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenMrsPatientRegistrationE2ETest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void driverSetup(){
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test
    public void patientRegisterPositive(){
        LoginPage loginPage = new LoginPage(driver,softAssert);
        loginPage.login();

    }

}
