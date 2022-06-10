package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegisterPage {
    public WebDriver driver;
    public SoftAssert softAssert;
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

    @FindBy(xpath = "//label[contains(.,\'What's the patient's birth date\')]")
    private WebElement birthDateLabel;

    @FindBy(xpath = "label[for='birthdateDay-field']")
    private WebElement birthDateDayLabel;

    @FindBy(xpath = "label[for=birthdateMonth-field']")
    private WebElement birthDateMonthLabel;

    @FindBy(xpath = "label[for=birthdateYear-field']")
    private WebElement birthDateYearLabel;

    @FindBy(id = "birthdateDay-field")
    private WebElement birthDateDayInput;

    @FindBy(id = "birthdateMonth-field")
    private WebElement birthDateMonthInput;

    @FindBy(id = "birthdateYear-field")
    private WebElement birthDateYearInput;

    @FindBy(xpath = "//h3[contains(text(),'Or')]")
    private WebElement orLabel;

    @FindBy(xpath = "//label[contains(.,\"What is the patient's address?\")]")
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

    @FindBy(xpath = "//label[contains(.,\"What's the patient phone number?\")]")
    private WebElement phoneNumberLabel;

    @FindBy(name = "phoneNumber")
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

    public void register(Map<String,String> patientData) {
        //Name
        softAssert.assertTrue(homeIconBtn.isEnabled());
        softAssert.assertTrue(registerPatientHeader.isDisplayed());
        softAssert.assertTrue(nameLabel.isDisplayed());
        softAssert.assertTrue(firstNameInput.isDisplayed());
        softAssert.assertTrue(middleNameInput.isDisplayed());
        softAssert.assertTrue(familyNameInput.isDisplayed());
        softAssert.assertTrue(unidentifiedPatientCheckBox.isEnabled());
        softAssert.assertTrue(nextBtn.isEnabled());

        String firstName = patientData.get("givenName");
        String middleName = patientData.get("middleName");
        String lastName = patientData.get("familyName");
        firstNameInput.sendKeys(firstName);
        middleNameInput.sendKeys(middleName);
        familyNameInput.sendKeys(lastName);
        nextBtn.click();
// Gender
        softAssert.assertTrue(previousBtn.isEnabled());
        softAssert.assertTrue(nextBtn.isEnabled());
        softAssert.assertTrue(genderLabel.isDisplayed());

        if (patientData.get("gender").equalsIgnoreCase("male")) {
            genderMale.click();
        } else {
            genderFemale.click();
        }
        nextBtn.click();
//Birthdate
        softAssert.assertTrue(birthDateLabel.isDisplayed());
        softAssert.assertTrue(birthDateDayLabel.isDisplayed());
        softAssert.assertTrue(birthDateMonthLabel.isDisplayed());
        softAssert.assertTrue(birthDateYearLabel.isDisplayed());
        softAssert.assertTrue(birthDateDayInput.isEnabled());
        softAssert.assertTrue(birthDateMonthInput.isEnabled());
        softAssert.assertTrue(birthDateYearInput.isEnabled());

        String birthDate = patientData.get("birthdate");
        String[] dates = birthDate.split("/");

        birthDateDayInput.sendKeys(dates[1]);
        birthDateYearInput.sendKeys(dates[2]);
        select = new Select(birthDateMonthInput);
        select.selectByVisibleText(dates[0]);
        nextBtn.click();
//Address
        softAssert.assertTrue(addressLabel.isDisplayed());
        softAssert.assertTrue(addressInput.isEnabled());
        softAssert.assertTrue(address2Input.isEnabled());
        softAssert.assertTrue(cityVillageInput.isEnabled());
        softAssert.assertTrue(stateProvinceInput.isEnabled());
        softAssert.assertTrue(countryInput.isEnabled());
        softAssert.assertTrue(postalCodeInput.isEnabled());

        addressInput.sendKeys(patientData.get("address"));
        address2Input.sendKeys(patientData.get("address2"));
        cityVillageInput.sendKeys(patientData.get("city"));
        stateProvinceInput.sendKeys(patientData.get("state"));
        countryInput.sendKeys(patientData.get("country"));
        postalCodeInput.sendKeys(patientData.get("postalcode"));
        nextBtn.click();
        //Phone number

        softAssert.assertTrue(phoneNumberLabel.isDisplayed());
        softAssert.assertTrue(phoneNumberInput.isEnabled());

        phoneNumberInput.sendKeys(patientData.get("phoneNumber"));
        nextBtn.click();
        //Relatives
        softAssert.assertTrue(relatedLabel.isDisplayed());
        softAssert.assertTrue(relationshipTypeSelector.isEnabled());
        softAssert.assertTrue(personNameInput.isEnabled());
        softAssert.assertTrue(addNewRelatedPersonIcon.isEnabled());
        softAssert.assertTrue(removeRelatedPersonIcon.isEnabled());

        select = new Select(relationshipTypeSelector);
        select.selectByVisibleText(patientData.get("relations"));
        personNameInput.sendKeys(patientData.get("personName"));
        nextBtn.click();
        //Confirm
        //String[] nameValue = String.valueOf(confirmList.indexOf(0)).split(":");
        verifyConfirm(patientData);
    }
    private void verifyConfirm(Map<String, String> patientData) {
        softAssert.assertTrue(cancelBtn.isEnabled());
        softAssert.assertEquals(confirmList.size(), 6);

        String[] nameArr = confirmList.get(0).getText().trim().split(":");
        String confirmName = nameArr[1];
        String expectedName = null;
        if (patientData.get("middleNameHas").equals("y")) {
            expectedName = patientData.get("givenName") + ", " + patientData.get("middleName") + ", "
                    + patientData.get("familyName");
        } else
            expectedName = patientData.get("givenName") + ", " + patientData.get("familyName");
        softAssert.assertEquals(confirmName, expectedName);

        softAssert.assertTrue(confirmBtn.isEnabled());
        confirmBtn.click();

    }
}
