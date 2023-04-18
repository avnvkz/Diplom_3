package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.client.UserClient;
import site.nomoreparties.stellarburgers.model.LoginPage;
import site.nomoreparties.stellarburgers.model.RegisterPage;
import site.nomoreparties.stellarburgers.model.user.User;
import site.nomoreparties.stellarburgers.model.user.UserCredentials;
import site.nomoreparties.stellarburgers.model.user.UserGenerator;
import static org.junit.Assert.assertEquals;


public class RegisterPageTests {

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
    @DisplayName("Успешная регистрация с валидными данными")
    public void checkRegisterWithValidDataSuccess() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.openRegisterPage();

        User user = UserGenerator.getRandom();
        objRegisterPage.fillRegisterForm(user);
        objRegisterPage.clickRegisterButton();

        String expectedUrl = objRegisterPage.getRegisterUrl();
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);

        accessToken = userClient.loginUser(UserCredentials.from(user));
    }

    @Test
    @DisplayName("Ошибка при регистрации с невалидными данными")
    public void checkRegisterWithInvalidDataFailed() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.openRegisterPage();

        User user = UserGenerator.getRandom();
        String passwordInvalid = UserGenerator.getRandomInvalidPassword();
        user.setPassword(passwordInvalid);
        objRegisterPage.fillRegisterForm(user);
        objRegisterPage.clickRegisterButton();

        String expectedMessage = "Некорректный пароль";
        String actualMessage = objRegisterPage.checkRegisterMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
