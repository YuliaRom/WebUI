package org.example.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class CrmTests {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://crm.geekbrains.space/user/login");
        login(driver);
        newContactPerson(driver);
        newProject(driver);

    }

    static void login(WebDriver driver) {
        WebElement element = driver.findElement(By.id("prependedInput"));
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }

    static void newContactPerson(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//ul[@class='nav nav-multilevel main-menu']/li/a/span[.='Контрагенты']"))).build().perform();
        driver.findElement(By.xpath("//span[.='Контактные лица']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[.='Создать контактное лицо']")).click();

        driver.findElement(By.xpath("//input[contains(@id, 'crm_contact_lastName')]")).sendKeys("Иванькин");
        driver.findElement(By.xpath("//input[contains(@id, 'crm_contact_firstName')]")).sendKeys("Иван");

        driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_contact_company')]")).click();
        driver.findElement(By.xpath("//div[text()='1234']")).click();

        driver.findElement(By.xpath("//input[contains(@id, 'crm_contact_jobTitle')]")).sendKeys("Менеджер");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]")).click();

    }

    static void newProject(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[.='Проекты']/ancestor::a"))).build().perform();
        driver.findElement(By.xpath("//span[.='Все проекты']")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[.='Создать проект']")).click();

        //при каждом запуске необходимо указывать уникальное имя, иначе проект не будет создан
        driver.findElement(By.xpath("//input[contains(@id, 'crm_project_name')]")).sendKeys("GreatProject7");

        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[text()='1234']")).click();

        Select selectBusinessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        selectBusinessUnit.selectByVisibleText("Research & Development");

        Select selectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        selectCurator.selectByVisibleText("Юзеров Юзер");

        Select selectRP = new Select(driver.findElement(By.name("crm_project[rp]")));
        selectRP.selectByVisibleText("Киселева Диана");

        Select selectAdministrator = new Select(driver.findElement(By.name("crm_project[administrator]")));
        selectAdministrator.selectByVisibleText("Чернецкий Евгений");

        Select selectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        selectManager.selectByVisibleText("Чернецкий Евгений");

        driver.findElement(By.xpath("//button[contains(., 'Сохранить и закрыть')]")).click();
        Thread.sleep(2000);

        driver.quit();
    }
}
