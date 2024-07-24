import Data.DataCreator;
import Data.Finals;
import Data.User;
import api.UserClient;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikum.pages.*;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest {

    WebDriver driver;
    UserClient userClient;
    User user;
    private String token;

    @Before
    public void setUp() {
        BasePage basePage = new BasePage(driver);
        driver = basePage.setUpDriver();
        RestAssured.baseURI = Finals.BASE_URI_FOR_API;
        userClient = new UserClient();
        user = DataCreator.generateRandomUser();
        Response response = userClient.createUser(user);
        token = response.path("accessToken");
    }

    @After
    public void tearDown() {
        if (token != null) {
            Response response = userClient.deleteUser(token);
            System.out.println("Attempting to delete user with token: " + token);
            response.then()
                    .statusCode(202)
                    .and()
                    .body("success", equalTo(true));
        } else {
            System.out.println("Token is null, skipping user deletion.");
        }
        driver.quit();
    }

    private MainPage loginUser() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickOnLoginInAccountButton();
        loginPage.setDataForLogin(user.getEmail(), user.getPassword());
        return loginPage.clickOnLoginButton();
    }

    @Test
    @Description("Тест логина с кнопкой Войти в аккаунт")
    public void loginWithLoginButtonTest() throws InterruptedException {
        MainPage mainPage = loginUser();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

    @Test
    @Description("Тест логина с кнопкой Личный кабинет")
    public void loginWithPersonalAccountButtonTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickOnPersonalAccountButton();
        loginPage.setDataForLogin(user.getEmail(), user.getPassword());
        mainPage = loginPage.clickOnLoginButton();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

    @Test
    @Description("Тест логина с ссылкой логина на странице Регистрации")
    public void loginWithLoginLinkTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickOnLoginInAccountButton();
        RegistrationPage registrationPage = loginPage.clickOnRegistrationLink();
        registrationPage.clickOnLoginLink();
        loginPage.setDataForLogin(user.getEmail(), user.getPassword());
        mainPage = loginPage.clickOnLoginButton();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

    @Test
    @Description("Тест логина со страницы Сброса пароля")
    public void loginWithLoginLinkInForgotPasswordPageTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickOnLoginInAccountButton();
        ForgotPasswordPage forgotPasswordPage = loginPage.clickOnForgotPasswordLink();
        forgotPasswordPage.clickOnLoginLink();
        loginPage.setDataForLogin(user.getEmail(), user.getPassword());
        mainPage = loginPage.clickOnLoginButton();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

}
