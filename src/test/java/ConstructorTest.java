import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikum.pages.BasePage;
import org.praktikum.pages.MainPage;

public class ConstructorTest {

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
    @Description("Тест перехода в категорию Булок")
    public void bunTest() {
        MainPage mainPage = new MainPage(driver);
        boolean isContains = mainPage.bunClassCheck();
        Assert.assertTrue(isContains);
    }

    @Test
    @Description("Тест перехода в категорию Соусов")
    public void sauceTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSauceCategory();
        Assert.assertTrue(mainPage.sauceClassCheck());
    }

    @Test
    @Description("Тест перехода в категорию Начинок")
    public void fillingTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnFillingCategory();
        Assert.assertTrue(mainPage.fillingClassCheck());
    }
}
