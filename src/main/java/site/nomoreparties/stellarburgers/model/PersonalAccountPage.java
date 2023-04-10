package site.nomoreparties.stellarburgers.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PersonalAccountPage {
    private static final String urlPersonalAccountPage = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By constructorButton = By.xpath(".//header/nav/ul/li[1]/a");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By profileButton = By.xpath(".//a[@href = '/account/profile']");
    private final By logoutButton = By.xpath(".//button[@class = 'Account_button__14Yp3 text text_type_main-medium text_color_inactive']");
    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) { this.driver = driver; }

    public void openPersonalAccountPage() { driver.get(urlPersonalAccountPage); }

    public String getUrlPersonalAccountPage() {
        return urlPersonalAccountPage;
    }

    public void waitLoadPersonalAccountPage() {
        waitElementLoadOnPage(profileButton);
    }

    public void waitElementLoadOnPage (By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void clickConstructorButton() {
        waitElementLoadOnPage(constructorButton);
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        waitElementLoadOnPage(logoButton);
        driver.findElement(logoButton).click();
    }

    public void clickLogoutButton() {
        waitElementLoadOnPage(logoutButton);
        driver.findElement(logoutButton).click();
    }

}
