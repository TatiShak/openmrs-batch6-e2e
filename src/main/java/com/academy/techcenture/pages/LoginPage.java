package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class LoginPage {

    public WebDriver driver;
    public SoftAssert softAssert;

    public LoginPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "w-auto")
    private WebElement loginLogo;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement userNameLabel;

    @FindBy(xpath ="//label[@for='password']")
    private WebElement passwordLabel;

    @FindBy(xpath = "//label[@for='sessionLocation']")
    private WebElement locationLabel;

    @FindBy(xpath = "//ul[@class='select']/li")
    private List<WebElement> locationList;

    @FindBy(id = "cantLogin")
    private WebElement cantLoginBtn;

    @FindBy(id = "loginButton")
    private WebElement loginBtn;

    @FindBy(id = "togglePassword")
    private WebElement togglePassword;



    public void login(){
        softAssert.assertTrue(userName.isEnabled());
        softAssert.assertTrue(password.isEnabled());
        softAssert.assertTrue(loginBtn.isEnabled());
        softAssert.assertTrue(userNameLabel.isDisplayed());
        softAssert.assertTrue(passwordLabel.isDisplayed());
        softAssert.assertEquals(driver.getTitle(), "Login");
        softAssert.assertEquals(locationList.size(), 6);
        softAssert.assertTrue(cantLoginBtn.isEnabled());

        userName.sendKeys(ConfigReader.getProperty("username"));
        password.sendKeys(ConfigReader.getProperty("password"));
        locationList.get(5).click();
        loginBtn.click();

    }









}
