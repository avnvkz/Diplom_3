package site.nomoreparties.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {

    private static final String urlMainPage = "https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//a[@href = '/account']");
    private final By signInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private final By pageTitle = By.xpath(".//h1[@class = 'text text_type_main-large mb-5 mt-10']");
    private final By bunHeader = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Булки']");
    private final By sauceButton = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Соусы']");
    private final By sauceHeader = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Соусы']");
    private final By fillingButton = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Начинки']");
    private final By fillingHeader = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Начинки']");
    private final WebDriver driver;

    public MainPage(WebDriver driver) { this.driver = driver; }
    @Step("Открыть главную страницу")
    public void openMainPage() { driver.get(urlMainPage); }

    public String getUrlMainPage() {
        return urlMainPage;
    }
    @Step("Ожидание загрузки главной страницы")
    public void waitLoadMainPage() {
        waitElementLoadOnPage(pageTitle);
    }
    public void waitElementLoadOnPage (By element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(element));
    }
    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickLoginButton () {
        waitElementLoadOnPage(loginButton);
        driver.findElement(loginButton).click();
    }
    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickSignInButton() {
        waitElementLoadOnPage(signInButton);
        driver.findElement(signInButton).click();
    }
    @Step("Раздел 'Булки' отображается")
    public boolean visibleBunHeader() {
        waitElementLoadOnPage(bunHeader);
        return driver.findElement(bunHeader).isDisplayed();
    }
    @Step("Нажать таб 'Соусы'")
    public void clickSauceButton() {
        waitElementLoadOnPage(sauceButton);
        driver.findElement(sauceButton).click();
    }
    @Step("Раздел 'Соусы' отображается")
    public boolean visibleSauceHeader() {
        waitElementLoadOnPage(sauceHeader);
        return driver.findElement(sauceHeader).isDisplayed();
    }
    @Step("Нажать таб 'Начинки'")
    public void clickFillingButton() {
        waitElementLoadOnPage(fillingButton);
        driver.findElement(fillingButton).click();
    }
    @Step("Раздел 'Начинки' отображается")
    public boolean visibleFillingHeader() {
        waitElementLoadOnPage(fillingHeader);
        return driver.findElement(fillingHeader).isDisplayed();
    }
}
