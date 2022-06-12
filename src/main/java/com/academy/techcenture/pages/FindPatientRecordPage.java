package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class FindPatientRecordPage {
    public WebDriver driver;
    public SoftAssert softAssert;

    public FindPatientRecordPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension")
    private WebElement findPatientRecordPage;

//    @FindBy(xpath = "//a[contains(.,'Find Patient Record')]")
//    private WebElement findPatientRecordPage;

    @FindBy(xpath = "//h2[contains(.,'Find Patient Record')]")
    private WebElement findPatientRecordPageHeader;

    @FindBy(xpath = "//input[@id='patient-search']")
    private WebElement patientSearchBarInput;

    @FindBy(xpath = "//td")
    private List<WebElement> tableAllElementsList;

    @FindBy(xpath = "//a[@href='/openmrs/index.htm']")
    private WebElement homeBtn;

    @FindBy(xpath = "//a[contains(.,'Logout')]")
    private WebElement logoutBtn;


public  void verifyFindPatientRecordPage (Map<String, String> patientData){

    softAssert.assertTrue(findPatientRecordPage.isEnabled());
    findPatientRecordPage.click();

    softAssert.assertTrue(findPatientRecordPageHeader.isDisplayed());
    softAssert.assertTrue(patientSearchBarInput.isEnabled());
    patientSearchBarInput.sendKeys(patientData.get("patientId"));
    softAssert.assertEquals(tableAllElementsList.get(0).getText().substring(0,6),patientData.get("patientId"));

    String firstMiddleFamilyName = patientData.get("givenName") + " " + patientData.get("middleName") + " " + patientData.get("familyName");
    String firstFamilyName = patientData.get("givenName") + " " + patientData.get("familyName");

    if (patientData.get("middleNameHas").equals("y")) {
        softAssert.assertEquals(tableAllElementsList.get(1).getText().trim(), firstMiddleFamilyName);
    } else {
        softAssert.assertEquals(tableAllElementsList.get(1).getText().trim(), firstFamilyName.trim(), "this name doesnt work");
    }
    softAssert.assertEquals(tableAllElementsList.get(2).getText(),patientData.get("gender").substring(0,1));
    softAssert.assertEquals(tableAllElementsList.get(3).getText(),  Integer.parseInt(patientData.get("currentAge").substring(0,2)) +"", "age did not match");

    String[] birthdateExcel = patientData.get("birthdate").split("/");
    String birthdateExcelAbbreviation = birthdateExcel[0].substring(0, 3);
    String birthdayFormatted = birthdateExcel[1] + "." + birthdateExcelAbbreviation + "." + birthdateExcel[2];
    softAssert.assertEquals(tableAllElementsList.get(4).getText().trim(),birthdayFormatted);


    softAssert.assertTrue(homeBtn.isEnabled());
    softAssert.assertTrue(logoutBtn.isEnabled());
    driver.navigate().back();
    logoutBtn.click();
}
}