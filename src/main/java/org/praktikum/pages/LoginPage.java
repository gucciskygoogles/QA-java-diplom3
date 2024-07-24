package org.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{

    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By loginButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    private final By goToRegistrationPageLink = By.linkText("Зарегистрироваться");
    private final By forgotPasswordLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод данных для логина")
    public void setDataForLogin(String email, String password) throws InterruptedException {
        Thread.sleep(200); // без этой штуки не работает, не понимаю почему
        setField(emailField, email);
        setField(passwordField, password);
    }

    @Step("Переход на основную страницу будучи авторизованным путем нажатия на кнопку Войти")
    public MainPage clickOnLoginButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Переход на страницу регистрации по ссылке")
    public RegistrationPage clickOnRegistrationLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(goToRegistrationPageLink));
        driver.findElement(goToRegistrationPageLink).click();
        return new RegistrationPage(driver);
    }

    @Step("Переход на страницу смены пароля путем нажатия на ссылку под формой входа")
    public ForgotPasswordPage clickOnForgotPasswordLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(forgotPasswordLink));
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Проверка наличия ссылки на страницу Регистрации")
    public boolean isRegistrationLinkPresent() {
        return isElementPresent(goToRegistrationPageLink);
    }
}
