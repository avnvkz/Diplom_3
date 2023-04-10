package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private static final String urlMainPage = "https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//a[@href = '/account']");
    private final By signInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    private final By pageTitle = By.xpath(".//h1[@class = 'text text_type_main-large mb-5 mt-10']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) { this.driver = driver; }

    public void openMainPage() { driver.get(urlMainPage); }

    public String getUrlMainPage() {
        return urlMainPage;
    }

    public void waitLoadMainPage() {
        waitElementLoadOnPage(pageTitle);
    }
    public void waitElementLoadOnPage (By element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void clickLoginButton () {
        waitElementLoadOnPage(loginButton);
        driver.findElement(loginButton).click();
    }

    public void clickSignInButton() {
        waitElementLoadOnPage(signInButton);
        driver.findElement(signInButton).click();
    }
}
