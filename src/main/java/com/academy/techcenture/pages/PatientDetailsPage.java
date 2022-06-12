package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class PatientDetailsPage {
    private WebDriver driver;
    private SoftAssert softAssert;

    public PatientDetailsPage(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'toast-container toast-position-top-right')]")
    private WebElement popUpAddPatientConfirmBox;

    @FindBy(className = "PersonName-givenName")
    private WebElement personGivenName;

    @FindBy(className = "PersonName-middleName")
    private WebElement middleName;

    @FindBy(className = "PersonName-familyName")
    private WebElement personFamilyName;

    @FindBy(xpath = "//div[@class='gender-age col-auto']")
    private WebElement genderAgeDob;

    @FindBy(xpath = "//div[@class='float-sm-right']//span")
    public WebElement patientID;

    @FindBy(className = "icon-sticky-note")
    private WebElement stickyNote;

    @FindBy(xpath = "//form[contains(@class,'editable')]//textarea")
    private WebElement stickyNoteInput;

    @FindBy(xpath = "//span[contains(@class,'icon-ok icon-white')]")
    private WebElement okBtn;

    @FindBy(xpath = "//span[@class='icon-remove']")
    private WebElement removeBtn;

    @FindBy(xpath = "//div[contains(@class,'toast-container toast-position-top-right')]")
    private WebElement popUpWindowStickyNote;

    @FindBy(xpath = "//pre")
    private WebElement stickyNoteDisplayed;

    @FindBy(xpath = "//div[@class='info-header']")
    private List<WebElement> dashboardWidgets;

    String[] dashboardWidgetsArray = {"DIAGNOSES", "VITALS", "LATEST OBSERVATIONS",
            "HEALTH TREND SUMMARY", "WEIGHT GRAPH", "APPOINTMENTS", "RECENT VISITS", "FAMILY", "CONDITIONS",
            "ATTACHMENTS", "ALLERGIES"};

    @FindBy(xpath = "//ul[@class='float-left']//li")
    private List<WebElement> generalActionsList;

    String[] generalActionsListArray = {"Start Visit", "Add Past Visit", "Merge Visits", "Schedule Appointment",
            "Request Appointment", "Mark Patient Deceased", "Edit Registration Information", "Delete Patient", "Attachments"};

    @FindBy(xpath = "//a[@href='/openmrs/index.htm']")
    private WebElement homeIconBtn;

    public void verifyPatientDetails(Map<String, String> patientData) {
//9. You will land on the patient details page. Verify top sections like given middle and family name, gender, dob and patient id.
        softAssert.assertEquals(personGivenName.getText(), patientData.get("givenName"));
        if (patientData.get("middleNameHas").equals("y")) {
            softAssert.assertEquals(middleName.getText(), patientData.get("middleName"));
        }
        softAssert.assertEquals(personFamilyName.getText(), patientData.get("familyName"));

       String[] genderAgeDobList= genderAgeDob.getText().trim().split(" ");
       softAssert.assertEquals(genderAgeDobList[0],patientData.get("gender"));

        // (03.Sep.2000) --> 03.Sep.2000 --> 03 Sep 2000
        String bDaySplit = genderAgeDobList[4];
        String birthDayWithoutParenthesis = bDaySplit.substring(1, bDaySplit.length() - 1);
        String[] birthDay = birthDayWithoutParenthesis.split("\\.");

        // March/17/1994 --> March 17 1994
        String[] birthdateExcel = patientData.get("birthdate").split("/");
        softAssert.assertEquals(birthDay[0], birthdateExcel[1]);
        String birthdateExcelAbbreviation = birthdateExcel[0].substring(0, 3);
        softAssert.assertEquals(birthDay[1].substring(0, 3), birthdateExcelAbbreviation);
        softAssert.assertEquals(birthDay[2], birthdateExcel[2]);

        //I did in Excel currentAge
        //int timeStamp = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
        int age = Integer.parseInt(genderAgeDobList[2]);
      softAssert.assertEquals(age,   Integer.parseInt(patientData.get("currentAge").substring(0,2)), "age did not match Patient details");

        for (int i = 0; i < generalActionsList.size() ; i++) {
            softAssert.assertEquals(generalActionsList.get(i).getText().trim(),generalActionsListArray[i]);
        }

        softAssert.assertTrue(stickyNote.isDisplayed());
        stickyNote.click();
        stickyNoteInput.sendKeys("Team 1 is done of this project!");
        softAssert.assertTrue(okBtn.isDisplayed());
        softAssert.assertTrue(removeBtn.isDisplayed());
        okBtn.click();
        softAssert.assertTrue(stickyNoteDisplayed.isDisplayed());




        softAssert.assertTrue(patientID.isDisplayed());
        String patientIDStr = patientID.getText();
        patientData.put("patientId", patientIDStr);


        softAssert.assertTrue(homeIconBtn.isEnabled());
        homeIconBtn.click();


    }
} //Female 23 years (05.Mar.1999)