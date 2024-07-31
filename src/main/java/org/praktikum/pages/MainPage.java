package org.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    private final By personalAccountButton = By.linkText("Личный Кабинет");
    private final By loginInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By makeOrderButton  = By.xpath("//button[text()='Оформить заказ']");
    private final By bunCategory = By.xpath("//span[text()='Булки']/parent::div");
    private final By sauceCategory = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingCategory = By.xpath("//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public LoginPage clickOnPersonalAccountButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажатие на кнопку Личный кабинет, когда пользователь авторизован")
    public ProfilePage clickOnPersonalAccountButtonWhenLogged() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    @Step("Клик по кнопке Вйоти в аккаунт")
    public LoginPage clickOnLoginInAccountButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(loginInAccountButton));
        driver.findElement(loginInAccountButton).click();
        return new LoginPage(driver);
    }

    public void clickOnBunCategory() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(bunCategory));
        driver.findElement(bunCategory).click();
    }

    @Step("Тест наличия в параметре class данных для проверки того, что выбрана категория Булки")
    public boolean bunClassCheck() {
        return doesElementClassContain(bunCategory, "tab_tab_type_current");
    }

    @Step("Тест наличия в параметре class данных для проверки того, что выбрана категория Соусы")
    public boolean sauceClassCheck() {
        return doesElementClassContain(sauceCategory, "tab_tab_type_current");
    }

    @Step("Тест наличия в параметре class данных для проверки того, что выбрана категория Начинки")
    public boolean fillingClassCheck() {
        return doesElementClassContain(fillingCategory, "tab_tab_type_current");
    }

    @Step("Нажатие на категорию Начинок")
    public void clickOnFillingCategory() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(fillingCategory));
        driver.findElement(fillingCategory).click();
    }

    @Step("Нажатие на категорию Соусов")
    public void clickOnSauceCategory() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(sauceCategory));
        driver.findElement(sauceCategory).click();
    }

    @Step("Проверка наличия кнопки Создать заказ на основной страницы")
    public boolean isMakeOrderButtonPresent() {
        return isElementPresent(makeOrderButton);
    }
}
