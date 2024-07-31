package org.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage{

    private final By nameOfUserField = By.xpath("//input[@name='Name']");

    private final By logPutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение данных из поля Имя")
    public String getNameOfUserField() {
        return getFieldValue(nameOfUserField);
    }

    @Step("Клик на кнопку Выход")
    public LoginPage clickOnLogoutButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(logPutButton));
        driver.findElement(logPutButton).click();
        return new LoginPage(driver);
    }

}
