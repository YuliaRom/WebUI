package org.example.lesson6;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    WebDriver driver;
    WebDriverWait webDriverWait;

    @FindBy(xpath = "//*[@href=\"/compose/\"]")
    public WebElement buttonNewLetter;

    @FindBy(xpath = "//span/span/span[contains(.,'Сохранить')]")
    public WebElement buttonSave;

    @FindBy(xpath = "//a[contains(.,'Черновики')]")
    public WebElement buttonDrafts;

    @FindBy(xpath = "//div/span[contains(@class,'ll-av__img')]")
    public WebElement moveToDrafts;

    @FindBy(xpath = "//div[contains(@class,'llc__avatar')]")
    public WebElement chooseDraft;

    @FindBy(xpath = "//span[contains(@title,'Удалить')]")
    public WebElement buttonDelete;

    @FindBy(xpath = "//a[contains(.,'Корзина')]")
    public WebElement buttonCart;

    @FindBy(xpath = "//span/span/span[contains(.,'Очистить')]")
    public WebElement buttonClear;

    @FindBy(xpath = "//span[contains(@class,'button2 button2_base button2_primary button2_fluid button2_hover-support')]")
    public WebElement buttonAgree;

    public void createLetter() throws InterruptedException {
        Actions actions = new Actions(driver);
        buttonNewLetter.click();
            }

    public void saveDraft() {
        buttonSave.click();
    }

    public void deleteDraft() throws InterruptedException {
        Actions actions = new Actions(driver);
        buttonDrafts.click();
        actions.moveToElement(moveToDrafts).build().perform();
        chooseDraft.click();
        buttonDelete.click();
    }

    public void removeFromCart() throws Exception {
        Actions actions = new Actions(driver);
        buttonCart.click();
        buttonClear.click();
        buttonAgree.click();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }
}
