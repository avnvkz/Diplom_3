package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.MainPage;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.user.User;
import site.nomoreparties.stellarburgers.model.user.UserGenerator;
import static org.junit.Assert.assertEquals;

public class LoginPageTests {

    private WebDriver driver;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void getStarted() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        userClient = new UserClient();
    }

    @After
    public void teardown() {
        driver.quit();

        if(accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешный вход с валидными данными")
    public void checkLoginWithValidDataSuccess() {
        User user = UserGenerator.getRandom();
        accessToken = userClient.createUser(user);

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();

        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitLoadMainPage();
        String expectedUrl = objMainPage.getUrlMainPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Ошибка при входе с невалидными данными")
    public void checkLoginWithInvalidDataFailed() {
        User user = UserGenerator.getRandom();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();

        String expectedUrl = objLoginPage.getUrlLoginPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }
}
