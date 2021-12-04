package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordPage {
    WebDriver driver;

    @FindBy(name = "password")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[contains(.,'Войти')]")
    public WebElement buttonInputPassword;

    public PasswordPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public void buttonInput() {
        buttonInputPassword.click();
            }

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
