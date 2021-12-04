package org.example.lesson6;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PageObjectTests {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUpBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 15);
        driver.get("https://account.mail.ru/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //Размер экрана не следует изменять, поскольку в меньшем размере часть кнопок сворачивается
        driver.manage().window().setSize(new Dimension(1700, 1700));
        login(driver);
    }


    void login(WebDriver driver) throws InterruptedException {
        new LoginPage(this.driver).fillLogin("test_gb_2021")
                .pushButtonInputPassword();
        new PasswordPage(this.driver).fillPassword("GeekBrains")
                .buttonInput();
        WebElement mainPage = driver.findElement(By.xpath("//div[contains(@class,'nav__folder nav__folder_parent')]"));
        assertThat(mainPage, isDisplayed());
    }

    @Order(1)
    @Test
    void createDraftTest() throws InterruptedException {
        new MainPage(driver).createLetter();
        new MainPage(driver).saveDraft();

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(@class,'notify__Посмотреть')]"));
        assertThat(mainPage, isDisplayed());
    }

    @Order(2)
    @Test
    void deleteDraftTest() throws InterruptedException {
        new MainPage(driver).deleteDraft();

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(.,'Перемещено в папку «Корзина»')]"));
        assertThat(mainPage, isDisplayed());
    }

    @Order(3)
    @Test
    void  removeFromCartTest() throws Exception {
        new MainPage(driver).removeFromCart();

        WebElement mainPage = driver.findElement(By.xpath("//span[contains(.,'В корзине пусто')]"));
        assertThat(mainPage, isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
