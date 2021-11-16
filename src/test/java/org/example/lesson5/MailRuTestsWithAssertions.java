package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
            login(driver);
        }

    static void login(WebDriver driver) throws InterruptedException {
        driver.get("https://account.mail.ru/login");
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("test_gb_2021");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Ввести пароль')]")).click();
        driver.findElement(By.name("password")).sendKeys("GeekBrains");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();
        Thread.sleep(5000);

    }

    @Test
    void createDraftTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//*[@href=\"/compose/\"]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span/span/span[contains(.,'Сохранить')]")).click();
        Thread.sleep(2000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
