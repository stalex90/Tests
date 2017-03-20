import org.apache.commons.io.FileUtils;
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
 * Created by Администратор on 28.02.2017.
 */
public class CabinetSellerTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    private static String URL=System.getProperty("url");
    static SelectFolder objSelectFolder;
    static CabinetSellerProfile objCabinet;
    static Waiters objWaiters;

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
            driver.get("https://dev2.pokupo.ru/login");
        }
        //driver.manage().window().maximize();
        objCabinet = new CabinetSellerProfile(driver);
        objCabinet.login("pokupomy1@gmail.com","1234qwer");
        objWaiters = new Waiters(driver);

    }


    @Test (description = "Вход")
    public void Login(){
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.panel));
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.profilePnl));
    }

    @Test (description = "Выход", dependsOnMethods = "Login")
    public void Logout(){
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.profilePnl));
        objCabinet.clickLogoutBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.loginFld));
    }

    @Test (description = "Переход в профиль", dependsOnMethods = "Login")
    public void GoProfile(){
        objCabinet.clickProfilePnl();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.description));
    }

    @Test (description = "Переход в персональные данные", dependsOnMethods = {"Login", "GoProfile"})
    public void GoPersonalData(){
        objCabinet.clickProfilePnl();
        objCabinet.clickPersonalData();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.secondName));
    }

    @Test (description = "Переход в верификацию", dependsOnMethods = {"Login", "GoProfile"})
    public void GoVerification(){
        objCabinet.clickProfilePnl();
        objCabinet.clickVerification();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.titelVerif));
    }

    @Test (description = "Переход в безопасность", dependsOnMethods = {"Login", "GoProfile"})
    public void GoSecurity(){
        objCabinet.clickProfilePnl();
        objCabinet.clickSecurity();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinet.newPassword));
    }

    @Test (description = "Сохранение профиля")
    public void CheckSaveProfile() throws InterruptedException {
        objCabinet.clickProfilePnl();
        objCabinet.inputDescription();
        objCabinet.inputWebsite();
        objCabinet.clickSave();
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        Assert.assertEquals(objCabinet.GetWebsite(),"https://devdashboard.pokupo.ru/" +objCabinet.randomInt);
        Assert.assertEquals(objCabinet.GetDescription(),"test" + objCabinet.randomInt);
    }

    @Test (description = "Переход в персональные данные")
    public void CheckPersonalData() throws InterruptedException {
        objCabinet.clickProfilePnl();
        objCabinet.clickPersonalData();
        objCabinet.waiter();
        objCabinet.selectGender("Женский");
        Thread.sleep(13000);
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
