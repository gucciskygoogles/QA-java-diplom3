package org.praktikum.pages;

import org.praktikum.data.Finals;
import org.praktikum.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    public WebDriver driver;
    public User user;
    private final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    public WebDriver setUpDriver() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.yandex.driver", Finals.PATH_TO_YANDEX_DRIVER);
            ChromeOptions options = new ChromeOptions();
            options.setBinary(Finals.PATH_TO_YANDEX_BROWSER);
            driver = new ChromeDriver(options);
        }
        else {
            throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
        driver.get(Finals.BASE_URI);
        return driver;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setField(By fieldLocator, String text) {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(fieldLocator));
        driver.findElement(fieldLocator).sendKeys(text);
    }

    public String getFieldValue(By fieldLocator) {
        WebElement element = null;
        for (int i = 0; i < 3; i++) { // Попробовать несколько раз
            try {
                new WebDriverWait(driver, 10).until(ExpectedConditions
                        .visibilityOfElementLocated(fieldLocator));
                element = driver.findElement(fieldLocator);
                return element.getAttribute("value");
            } catch (StaleElementReferenceException e) {
            }
        }
        throw new RuntimeException("Unable to find element value after several attempts: " + fieldLocator);
    }

    public boolean isElementPresent(By fieldLocator) {
        for (int i = 0; i < 3; i++) { // Попробовать несколько раз
            new WebDriverWait(driver, 10).until(ExpectedConditions
                    .visibilityOfElementLocated(fieldLocator));
            try {
                driver.findElement(fieldLocator);
                return true;
            } catch (StaleElementReferenceException e) {
            }
        }
        return false;
    }

    public MainPage clickOnLogo() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOfElementLocated(logo));
        driver.findElement(logo).click();
        return new MainPage(driver);
    }

    public void clickOnConstructorButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
        new MainPage(driver);
    }

    public boolean doesElementClassContain(By xpath, String substring) {
        String className = getClassOfCategory(xpath);
        return className != null && className.contains(substring);
    }

    public String getClassOfCategory(By xpath) {
        return driver.findElement(xpath).getAttribute("class");
    }


}
