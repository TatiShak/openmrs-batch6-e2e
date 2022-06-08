package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegisterPage {

    private WebDriver driver;
    private SoftAssert softAssert;
    private Select select;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//i[contains(@class,'icon-home')]")
    private WebElement homeIconBtn;

    @FindBy(xpath = "//h2[contains(text(),'Register a patient')]")
    private WebElement registerPatientHeader;

    @FindBy(xpath = "//h3[contains(text(),\"What's the\")]")
    private WebElement nameLabel;

    @FindBy(name = "givenName")
    private WebElement firstNameInput;

    @FindBy(name = "middleName")
    private WebElement middleNameInput;

    @FindBy(name = "familyName")
    private WebElement familyNameInput;

    @FindBy(xpath = "//input[@id='checkbox-unknown-patient']")
    private WebElement unidentifiedPatientCheckBox;

    @FindBy(id = "next-button")
    private WebElement nextBtn;

    @FindBy(id = "prev-button")
    private WebElement previousBtn;

    @FindBy(xpath = "//label[@for='gender-field']")
    private WebElement genderLabel;

    @FindBy(xpath = "//option[@value='M']")
    private WebElement genderMale;

    @FindBy(xpath = "//option[@value='F']")
    private WebElement genderFemale;

    @FindBy(xpath = "//label[@for='fr1961-field']")
    private WebElement birthDateLabel;

    @FindBy(xpath = "label[for='birthdateDay-field']")
    private WebElement birthDateDayLabel;

    @FindBy(id = "birthdateDay-field")
    private WebElement birthDateDayInput;

    @FindBy(id = "birthdateMonth-field")
    private WebElement birthDateMonthInput;

    @FindBy(id = "birthdateYear-field")
    private WebElement birthDateYearInput;

    @FindBy(xpath = "//h3[contains(text(),'Or')]")
    private WebElement orLabel;

    @FindBy(xpath = "//label[contains(@for,'fr1278-field')]")
    private WebElement addressLabel;

    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "cityVillage")
    private WebElement cityVillageInput;

    @FindBy(id = "stateProvince")
    private WebElement stateProvinceInput;

    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//label[@for='fr8770-field']")
    private WebElement phoneNumberLabel;

    @FindBy(id = "fr8625-field")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//h3[contains(.,'Who is the')]")
    private WebElement relatedLabel;

    @FindBy(id = "relationship_type")
    private WebElement relationshipTypeSelector;

    @FindBy(xpath = "//input[contains(@class,'person-typeahead')]")
    private WebElement personNameInput;

    @FindBy(xpath = "(//i[@class='icon-plus-sign edit-action'])[1]")
    private WebElement addNewRelatedPersonIcon;

    @FindBy(xpath = "(//i[@class='icon-minus-sign edit-action'])[1]")
    private WebElement removeRelatedPersonIcon;

    @FindBy(xpath = "//div[@id='dataCanvas']//p")
    private List<WebElement> confirmList;

    @FindBy(id = "submit")
    private WebElement confirmBtn;

    @FindBy(id = "cancelSubmission")
    private WebElement cancelBtn;


    public void verifyRegisterPage(Map<String, String> patientData) {
        softAssert.assertEquals(driver.getTitle(), "OpenMRS Electronic Medical Record");
        softAssert.assertTrue(homeIconBtn.isEnabled());
        softAssert.assertTrue(registerPatientHeader.isDisplayed());

        fillOutDemographics(patientData);
        fillOutContactInfo(patientData);
        fillOutRelationships(patientData);
        verifyConfirm(patientData);

    }

    private void fillOutDemographics(Map<String, String> patientData) {
        softAssert.assertTrue(nameLabel.isDisplayed());
        firstNameInput.sendKeys(patientData.get("givenName"));
        middleNameInput.sendKeys(patientData.get("middleName"));
        familyNameInput.sendKeys(patientData.get("familyName"));
        softAssert.assertTrue(unidentifiedPatientCheckBox.isDisplayed());
        softAssert.assertTrue(nextBtn.isDisplayed());
        nextBtn.click();

        String genderSelect = patientData.get("gender");
        if (genderSelect.equals("Female")) {
            genderFemale.click();
        } else {
            genderMale.click();
        }

        softAssert.assertTrue(nextBtn.isDisplayed());
        nextBtn.click();

        String birthdate = patientData.get("birthdate");
        String[] splitBD = birthdate.split(" ");
        String birthdateDay = splitBD[1];
        String birthdateMonth = splitBD[0];
        String birthdateYear = splitBD[2];
        select = new Select(birthDateDayInput);
        select.selectByValue(birthdateDay);
        select = new Select(birthDateMonthInput);
        select.selectByValue(birthdateMonth);
        select = new Select(birthDateYearInput);
        select.selectByValue(birthdateYear);
        softAssert.assertTrue(nextBtn.isDisplayed());
        nextBtn.click();

    }

    private void fillOutContactInfo(Map<String, String> patientData) {
        softAssert.assertTrue(addressLabel.isDisplayed());
        addressInput.sendKeys(patientData.get("address"));
        address2Input.sendKeys(patientData.get("address2"));
        cityVillageInput.sendKeys(patientData.get("city"));
        stateProvinceInput.sendKeys(patientData.get("state"));
        countryInput.sendKeys(patientData.get("country"));
        postalCodeInput.sendKeys(patientData.get("postalcode"));
        nextBtn.click();
        softAssert.assertTrue(phoneNumberLabel.isDisplayed());
        phoneNumberInput.sendKeys(patientData.get("phoneNumber"));
        nextBtn.click();

    }

    private void fillOutRelationships(Map<String, String> patientData) {
        softAssert.assertTrue(relatedLabel.isDisplayed());
        select = new Select(relationshipTypeSelector);
        select.selectByValue(patientData.get("relations"));
        personNameInput.sendKeys(patientData.get("personName"));
        softAssert.assertTrue(addNewRelatedPersonIcon.isEnabled());
        softAssert.assertTrue(removeRelatedPersonIcon.isDisplayed());
        nextBtn.click();

    }

    private void verifyConfirm(Map<String, String> patientData) {
        softAssert.assertTrue(cancelBtn.isEnabled());
        softAssert.assertEquals(confirmList.size(), 6);
        softAssert.assertTrue(confirmBtn.isEnabled());
        confirmBtn.click();

    }


}
