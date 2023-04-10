package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    /*@After
    public void teardown() {
        driver.quit();

        if(accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }*/

    @Test
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
