package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(name = "username")
    public WebElement inputLogin;

    @FindBy(xpath = "//button[contains(.,'Ввести пароль')]")
    public WebElement buttonInputPassword;

    public LoginPage fillLogin(String login) {
        inputLogin.sendKeys(login);
        return this;
    }

    public LoginPage pushButtonInputPassword() {
        buttonInputPassword.click();
        return this;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
