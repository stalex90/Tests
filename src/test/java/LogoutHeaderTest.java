import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 11.10.16.
 */
public class LogoutHeaderTest {

    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    RegistrationPage1 objRegistrationPage1;
    static OS_Version objOS_Version;
    private static String URL=System.getProperty("url");

    @BeforeMethod
    public static void openBrowser(){
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        //driver.manage().window().maximize();


    }

    @Test (description = "Проверка перехода на домашнюю страницу по логотипу")
    public void ReturnLogo() {

        objHomePage = new HomePage(driver);
        objHomePage.LogoImage_RetunToHomepage();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1");

    }

    @Test (description = "Проверка перехода на домашнюю страницу по названию магазина")
    public void ReturnText()  {

        objHomePage = new HomePage(driver);
        objHomePage.LogoText_RetunToHomepage();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1");

    }

    @Test (description = "Проверка открытия модуля аутентификации")
    public void ClickLoginBtn(){

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        Assert.assertEquals(objLoginPage.GetNameWidget(),"Войти");

    }

    @Test (description = "Проверка открытия модуля регистрации")
    public void ClickRegBtn(){

        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        Assert.assertEquals(objRegistrationPage1.GetNameWidget(),"Регистрация пользователя");

    }


    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
