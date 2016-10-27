import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 18.10.16.
 */
public class LoginModuleTest {

    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    RegistrationPage1 objRegistrationPage1;



    @BeforeMethod
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\Documents and Settings\\Admin\\IdeaProjects\\TestProject\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();


    }

    @Test (description = "Проверка аутентификации с валидными значениями")
    public void ValidLogin() {
        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.CompleteLogin();
        Assert.assertTrue(driver.findElement(objHomePage.getProfileIcon()).isDisplayed());

    }

    @Test (description = "Проверка повления ошибки логина")
    public void InValidLogin() {
        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.InvalidLoginComplete();
        Assert.assertTrue(driver.findElement(objLoginPage.getWarningMsg()).isDisplayed());
    }

    @Test (description = "Проверка повления ошибки пароля")
    public void InValidPassword() {
        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.InvalidPasswordComplete();
        Assert.assertTrue(driver.findElement(objLoginPage.getWarningMsg()).isDisplayed());
    }

    @Test (description = "Проверка открытия страницы восстановления пароля")
    public void ForgotPassword() throws InterruptedException {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.ClickForgotBtn();
        objLoginPage.SwitchNewTab();
        Assert.assertEquals(driver.findElement(By.xpath(".//h1[text()='Сброс пароля']")).getText(),"Сброс пароля");

    }

    @Test (description = "Проверка открытия страницы регистрации пользователя")
    public void RegistrationButton()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objRegistrationPage1 = objLoginPage.ClickRegBtn();
        Assert.assertEquals(objRegistrationPage1.GetNameWidget(),"Регистрация пользователя");

    }

    @Test (description = "Проверка закрытия модуля аутентификации")
    public void CloseButton()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.ClickCloseBtn();

    }

    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
