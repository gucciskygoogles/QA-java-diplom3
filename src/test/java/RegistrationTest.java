import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikum.pages.*;
import org.praktikum.tools.Tools;


public class RegistrationTest {

    private WebDriver driver;


    @Before
    public void setUp() {
        BasePage basePage = new BasePage(driver);
        driver = basePage.setUpDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Тест проверка регистрации пользователя UI")
    public void UIRegistrationTest() throws InterruptedException {
        String name = Tools.generateRandomName();
        String email = Tools.generateRandomEmail("yandex.ru", 5);
        String password = Tools.generateRandomPassword(7);

        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickOnPersonalAccountButton();
        RegistrationPage registrationPage = loginPage.clickOnRegistrationLink();

        registrationPage.setDataForRegistration(name, email, password);

        loginPage = registrationPage.clickOnRegistrationButton();
        loginPage.setDataForLogin(email, password);
        mainPage = loginPage.clickOnLoginButton();
        ProfilePage profilePage = mainPage.clickOnPersonalAccountButtonWhenLogged();
        Assert.assertEquals(name, profilePage.getNameOfUserField());
    }

    @Test
    @Description("Тест проверка регистрации пользователя с некорректным паролем UI")
    public void UIIncorrectPasswordRegistrationTest() {
        String name = Tools.generateRandomName();
        String email = Tools.generateRandomEmail("yandex.ru", 5);
        String password = Tools.generateRandomPassword(3);

        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickOnPersonalAccountButton();
        RegistrationPage registrationPage = loginPage.clickOnRegistrationLink();

        registrationPage.setDataForRegistration(name, email, password);

        registrationPage.clickOnRegistrationButton();
        Assert.assertTrue(registrationPage.isIncorrectPasswordMessagePresent());

    }
}
