package com.academy.techcenture.e2e;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.config.Driver;
import com.academy.techcenture.pages.HomePage;
import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.RegisterPage;
import com.academy.techcenture.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Map;

public class OpenMrsPatientRegistrationE2ETest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void driverSetup() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test
    public void patientRegisterPositive() {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        loginPage.login();
        homePage.clickOnRegisterPatientTab();

    }

    @Test(dataProvider = "newPatientData")
    public void fillOutRegisterPagePositive(Map<String, String> patientData) throws IOException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        RegisterPage registerPage = new RegisterPage(driver, softAssert);

        loginPage.login();
        homePage.clickOnRegisterPatientTab();
        registerPage.verifyRegisterPage(patientData);
    }


    @DataProvider(name = "newPatientData")
    public Object[][] getNewUsersData() {
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/patientData.xlsx", "patientDB");
        return excelReader.getData();
    }


    @AfterMethod
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }

}
