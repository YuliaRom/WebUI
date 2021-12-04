package org.example.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MailRuTests {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://account.mail.ru/login");
        login(driver);
        createDraft(driver);

    }

    static void login(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("test_gb_2021");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Ввести пароль')]")).click();
        driver.findElement(By.name("password")).sendKeys("GeekBrains");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(.,'Войти')]")).click();

        Thread.sleep(5000);

    }

    static void createDraft(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//*[@href=\"/compose/\"]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span/span/span[contains(.,'Сохранить')]")).click();
        Thread.sleep(2000);

        driver.quit();
    }
}
