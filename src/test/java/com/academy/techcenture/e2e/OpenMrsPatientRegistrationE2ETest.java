package com.academy.techcenture.e2e;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.config.Driver;
import com.academy.techcenture.pages.*;
import com.academy.techcenture.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class OpenMrsPatientRegistrationE2ETest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void driverSetup(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "newPatientData")
    public void patientRegisterPositive(Map<String, String> patientData){
        LoginPage loginPage = new LoginPage(driver,softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        RegisterPage registerPage = new RegisterPage(driver, softAssert);
        PatientDetailsPage patientDetailsPage=new PatientDetailsPage(driver,softAssert);
        FindPatientRecordPage findPatientRecordPage =new FindPatientRecordPage(driver,softAssert);
        loginPage.login();
        homePage.clickOnRegisterPatientTab();
        registerPage.register(patientData);
        patientDetailsPage.verifyPatientDetails(patientData);
        findPatientRecordPage.verifyFindPatientRecordPage(patientData);

       softAssert.assertAll();

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
