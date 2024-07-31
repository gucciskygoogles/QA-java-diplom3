package org.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {


    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordMessage = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath("//a[@href='/login']");
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод данных для регистрации")
    public void setDataForRegistration(String name, String email, String password) {
        setField(nameField, name);
        setField(emailField, email);
        setField(passwordField, password);
    }

    @Step("Клик на кнопку Зарегистрироваться")
    public LoginPage clickOnRegistrationButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(registrationButton));
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик на ссылку Логина под формой регистрации")
    public LoginPage clickOnLoginLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(loginLink));
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Проверка наличия сообщения о некорректном пароле")
    public boolean isIncorrectPasswordMessagePresent() {
        return isElementPresent(incorrectPasswordMessage);
    }

}
