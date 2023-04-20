package site.nomoreparties.stellarburgers.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.model.user.User;

import java.time.Duration;

public class LoginPage {

   private static final String urlLoginPage = "https://stellarburgers.nomoreparties.site/login";
   private final By authForm = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']");
   private final By loginFormEmail = By.xpath(".//input[@name = 'name']");
   private final By loginFormPassword = By.xpath(".//input[@name = 'Пароль']");
   private final By signInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
   private final By registerButton = By.xpath(".//a[@class = 'Auth_link__1fOlj']");
   private final WebDriver driver;

   public LoginPage(WebDriver driver) { this.driver = driver; }
   @Step("Открыть страницу для логина")
   public void openLoginPage() { driver.get(urlLoginPage); }
   public String getUrlLoginPage() {
      return urlLoginPage;
   }
   @Step("Ожидание загрузки страницы логина")
   public void waitLoadLoginPage() {
      waitElementLoadOnPage(authForm);
   }

   public void waitElementLoadOnPage (By element) {
      new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(element));
   }

   public void clickRegisterButton() {
      waitElementLoadOnPage(registerButton);
      driver.findElement(registerButton).click();
   }

   public void fillField(By element, String value) {
      waitElementLoadOnPage(element);
      driver.findElement(element).sendKeys(value);
   }
   @Step("Заполнение формы логина")
   public void fillLoginForm(User user) {
      fillField(loginFormEmail, user.getEmail());
      fillField(loginFormPassword, user.getPassword());
   }
   @Step("Нажать кнопку 'Вход'")
   public void clickSignInButton() {
      waitElementLoadOnPage(signInButton);
      driver.findElement(signInButton).click();
   }
}
