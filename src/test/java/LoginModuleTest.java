import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 18.10.16.
 */
public class LoginModuleTest {

    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    RegistrationPopup objReg;
    static OS_Version objOS_Version;
    private static String URL=System.getProperty("url");
    static SelectFolder objSelectFolder;
    static Waiters objWait;


    @BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        objSelectFolder = new SelectFolder();
        String s = objSelectFolder.folderName();
        if (objOS_Version.isUnix()) {
            File myPath = new File(s);
            myPath.mkdir();
            String path = s;
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (objOS_Version.isUnix()) {
            driver.get(URL);}
        if (objOS_Version.isWindows()){
            driver.get("http://promodev56.pokupo.ru/shop/1");
        }
        //driver.manage().window().maximize();
        objWait = new Waiters(driver);

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
        objReg = new RegistrationPopup(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.ClickRegBtn();
        Assert.assertTrue(objWait.isElementPresentWaiters(objReg.emailORphone));

    }

    @Test (description = "Проверка закрытия модуля аутентификации")
    public void CloseButton()  {

        objHomePage = new HomePage(driver);
        objLoginPage = objHomePage.ClickLoginBtn();
        objLoginPage.ClickCloseBtn();

    }

    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        objSelectFolder = new SelectFolder();
        String s = objSelectFolder.folderName();
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = s + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }

}
