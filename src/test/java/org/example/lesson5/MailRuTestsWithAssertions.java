package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
import java.util.concurrent.TimeUnit;


public class MailRuTestsWithAssertions {
    WebDriverWait WebDriverWait;
    WebDriver driver;

        @BeforeAll
        static void setupDriver () {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        void initBrowser () throws InterruptedException {
            driver = new ChromeDriver();
            WebDriverWait = new WebDriverWait(driver, 15);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //Размер экрана не следует изменять, поскольку в меньшем размере часть кнопок сворачивается
            driver.manage().window().setSize(new Dimension(1700, 1700));
            login(driver);
        }

    static void login(WebDriver driver) throws InterruptedException {
        driver.get("https://account.mail.ru/login");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("test_gb_2021");
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Ввести пароль')]")).click();
        driver.findElement(By.name("password")).sendKeys("GeekBrains");
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();
        //Thread.sleep(5000);

    }

    @Test
    void createDraftTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//*[@href=\"/compose/\"]")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//span/span/span[contains(.,'Сохранить')]")).click();
        //Thread.sleep(1000);

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(@class,'notify__Посмотреть')]"));
        assertThat(mainPage, isDisplayed());
    }

    @Test
    void deleteDraftTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//a[contains(.,'Черновики')]")).click();
        WebDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/span[contains(@class,'ll-av__img')]"))));
        //Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.xpath("//div/span[contains(@class,'ll-av__img')]"))).build().perform();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(@class,'llc__avatar')]")).click();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(@title,'Удалить')]")).click();
        //Thread.sleep(2000);

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(.,'Перемещено в папку «Корзина»')]"));
        assertThat(mainPage, isDisplayed());
        //Thread.sleep(2000);
    }

    //В корзине должны быть письма, иначе тест будет не пройден
    @Test
    void removeFromCartTest() {
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//a[contains(.,'Корзина')]")).click();
        driver.findElement(By.xpath("//span/span/span[contains(.,'Очистить')]")).click();
        driver.findElement(By.xpath("//span[contains(@class,'button2 button2_base button2_primary button2_fluid button2_hover-support')]")).click();

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(.,'В корзине пусто')]"));
        assertThat(mainPage, isDisplayed());

    }

    @AfterEach
    void tearDown() {
            driver.quit();
    }
}
