package org.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage extends BasePage {
    private final By loginLink = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на ссылку для перехода на страницу Логина")
    public LoginPage clickOnLoginLink() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(loginLink));
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
