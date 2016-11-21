import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12.10.16.
 */
public class LoginHeaderTest {
    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    LogoutPopup objLogoutPopup;
    static OS_Version objOS_Version;

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();

    }

    @Test (description = "Проверка перехода на домашнюю страницу по логотипу")
    public void ReturnLogo() throws InterruptedException {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.LogoImage_RetunToHomepage();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1");

    }

    @Test (description = "Проверка перехода на домашнюю страницу по названию магазина")
    public void ReturnText()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.LogoText_RetunToHomepage();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1");

    }

    @Test (description = "Проверка открытия страницы профиль")
    public void CheckProfileOpens()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1#/profile/");

    }

    @Test (description = "Проверка открытия страницы заказы")
    public void CheckOrderOpens()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1#/purchases/block=list");

    }

    @Test (description = "Проверка открытия страницы сообщения")
    public void CheckMessageOpens()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        Assert.assertEquals(objHomePage.getURL(),"https://promodev.pokupo.ru/shop/1#/messages/");

    }

    @Test (description = "Проверка открытия выхода")
    public void CheckLogout()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        objHomePage.ClickProfileIcon();
        objLogoutPopup = objHomePage.ClickLogoutBtn();
        Assert.assertEquals(objLogoutPopup.GetWidgetName(),"Вы действительно хотите выйти?");

    }

    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
