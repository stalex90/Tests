import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
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
    static SelectFolder objSelectFolder;
    Waiters objWaiteres;

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
            driver.get("http://pokupotest.pokupo.ru/shop/1");
        }
        //driver.manage().window().maximize();

    }

    @Test (description = "Проверка перехода на домашнюю страницу по логотипу")
    public void ReturnLogo() throws InterruptedException {
        objWaiteres = new Waiters(driver);
        objHomePage = new HomePage(driver);
        objHomePage.LogoImage_RetunToHomepage();
        Thread.sleep(2000);
        Assert.assertTrue(objWaiteres.isElementPresentWaiters(objHomePage.LogoImage));

    }

    @Test (description = "Проверка перехода на домашнюю страницу по названию магазина")
    public void ReturnText() throws InterruptedException  {
        objWaiteres = new Waiters(driver);
        objHomePage = new HomePage(driver);
        objHomePage.LogoText_RetunToHomepage();
        Thread.sleep(2000);
        Assert.assertTrue(objWaiteres.isElementPresentWaiters(objHomePage.LogoImage));

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
