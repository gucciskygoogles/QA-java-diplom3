import org.praktikum.data.DataCreator;
import org.praktikum.data.Finals;
import org.praktikum.data.User;
import org.praktikum.api.UserClient;
import io.qameta.allure.Description;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikum.pages.BasePage;
import org.praktikum.pages.LoginPage;
import org.praktikum.pages.MainPage;
import org.praktikum.pages.ProfilePage;


import static org.hamcrest.CoreMatchers.equalTo;

public class AuthorizedUserTest {

    private WebDriver driver;
    private UserClient userClient;
    private User user;
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

    public MainPage loginUser() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickOnLoginInAccountButton();
        loginPage.setDataForLogin(user.getEmail(), user.getPassword());
        return loginPage.clickOnLoginButton();
    }


    @Test
    @Description("Тест перехода на конструктор с помощью кнопки Конструктор в Хэдере")
    public void goToConstructorWithConstructorButtonTest() throws InterruptedException {
        MainPage mainPage = loginUser();
        ProfilePage profilePage = mainPage.clickOnPersonalAccountButtonWhenLogged();
        profilePage.clickOnConstructorButton();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

    @Test
    @Description("Тест перехода на конструктор с помощью логотипа в Хэдере")
    public void goToConstructorWithLogoTest() throws InterruptedException {
        MainPage mainPage = loginUser();
        ProfilePage profilePage = mainPage.clickOnPersonalAccountButtonWhenLogged();
        profilePage.clickOnLogo();
        Assert.assertTrue(mainPage.isMakeOrderButtonPresent());
    }

    @Test
    @Description("Тест Выхода из аккаунта пользователя")
    public void logoutTest() throws InterruptedException {
        MainPage mainPage = loginUser();
        ProfilePage profilePage = mainPage.clickOnPersonalAccountButtonWhenLogged();
        LoginPage loginPage = profilePage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.isRegistrationLinkPresent());
    }
}
