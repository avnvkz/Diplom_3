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
import site.nomoreparties.stellarburgers.model.PersonalAccountPage;
import site.nomoreparties.stellarburgers.model.user.User;
import site.nomoreparties.stellarburgers.model.user.UserGenerator;
import static org.junit.Assert.assertEquals;

public class PersonalAccountTest {

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
    @DisplayName("Открытие страницы личного кабинета после входа")
    public void checkOpenPersonalAccountPageAfterLoginSuccess() {
        User user = UserGenerator.getRandom();
        accessToken = userClient.createUser(user);

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();

        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickLoginButton();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.waitLoadPersonalAccountPage();

        String expectedUrl = objPersonalAccountPage.getUrlPersonalAccountPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Открытие главной странице при клике на конопку 'Конструктор'")
    public void checkOpenMainPageClickConstructorButton() {
        User user = UserGenerator.getRandom();
        accessToken = userClient.createUser(user);
        LoginPage objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);

        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();
        objMainPage.clickLoginButton();
        objPersonalAccountPage.waitLoadPersonalAccountPage();
        objPersonalAccountPage.clickConstructorButton();
        objMainPage.waitLoadMainPage();

        String expectedUrl = objMainPage.getUrlMainPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Открытие главной страницы при нажатии на кнопку с логотипом 'Stellar-burgers'")
    public void checkOpenMainPageClickLogoButton() {
        User user = UserGenerator.getRandom();
        accessToken = userClient.createUser(user);
        LoginPage objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);

        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();
        objMainPage.clickLoginButton();
        objPersonalAccountPage.clickLogoButton();
        objMainPage.waitLoadMainPage();

        String expectedUrl = objMainPage.getUrlMainPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @DisplayName("Успешный выход из аккаунта при нажатии на кнопку 'Выход'")
    public void checkLogoutFromPersonalAccountPageSuccess() {
        User user = UserGenerator.getRandom();
        accessToken = userClient.createUser(user);

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.openLoginPage();
        objLoginPage.fillLoginForm(user);
        objLoginPage.clickSignInButton();

        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickLoginButton();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.waitLoadPersonalAccountPage();
        objPersonalAccountPage.clickLogoutButton();
        objLoginPage.waitLoadLoginPage();

        String expectedUrl = objLoginPage.getUrlLoginPage();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

}
