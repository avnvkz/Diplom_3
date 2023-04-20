package site.nomoreparties.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.model.user.User;

import java.time.Duration;

public class RegisterPage {

    private static final String urlRegisterPage = "https://stellarburgers.nomoreparties.site/register";
    private final By registerFieldName = By.xpath("//form/fieldset[1]/div/div/input");
    private final By registerFieldEmail = By.xpath("//form/fieldset[2]/div/div/input");
    private final By registerFieldPassword = By.xpath("//form/fieldset[3]/div/div/input");
    private final By registerFormButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By passwordInvalidMessage = By.xpath(".//form/fieldset[3]/div/p");
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) { this.driver = driver; }
    @Step("Открыть страницу регистрации")
    public void openRegisterPage() { driver.get(urlRegisterPage); }
    public String getRegisterUrl() {return urlRegisterPage;}
    public void waitElementLoadOnPage (By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void fillField(By element, String value) {
        waitElementLoadOnPage(element);
        driver.findElement(element).sendKeys(value);
    }
    @Step("Заполнить форму регистрации")
    public void fillRegisterForm(User user) {
        fillField(registerFieldName, user.getName());
        fillField(registerFieldEmail, user.getEmail());
        fillField(registerFieldPassword, user.getPassword());
    }
    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        waitElementLoadOnPage(registerFormButton);
        driver.findElement(registerFormButton).click();
    }


    public String checkRegisterMessage() {
        waitElementLoadOnPage(passwordInvalidMessage);
        return driver.findElement(passwordInvalidMessage).getText();
    }
}
