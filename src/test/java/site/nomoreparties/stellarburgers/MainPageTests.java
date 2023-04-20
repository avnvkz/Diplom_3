package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.MainPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageTests {

    private WebDriver driver;

    @Before
    public void getStarted() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Открыти страницы логина по кнопке 'Личный Кабинет'")
    public void checkOpenLoginPageClickButtonPersonalAccount() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        LoginPage objLoginPage = new LoginPage(driver);
        String expectedUrl = objLoginPage.getUrlLoginPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Открытие страницы логина по кнопке 'Войти в аккаунт'")
    public void checkOpenLoginPageClickButtonSignIn() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickSignInButton();
        LoginPage objLoginPage = new LoginPage(driver);
        String expectedUrl = objLoginPage.getUrlLoginPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Отображение вкладки 'Булки' на главной странице")
    public void checkBunOnTheMainPage() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        assertTrue(objMainPage.visibleBunHeader());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы' при клике на таб 'Соусы'")
    public void checkScrollToSauceClickSauceButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickSauceButton();
        assertTrue(objMainPage.visibleSauceHeader());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки' при клике на таб 'Начинки'")
    public void checkScrollToFillingClickFillingButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickFillingButton();
        assertTrue(objMainPage.visibleFillingHeader());
    }
}
