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
 * Created by Администратор on 08.02.2017.
 */
public class UrlTest {

        static WebDriver driver;
        HomePage objHomePage;
        LoginPage objLoginPage;
        Catalog objCatalog;
        Cart objCart;
        Oformit objOformit;
        static OS_Version objOS_Version;
        static SelectFolder objSelectFolder;

        private static String URL=System.getProperty("url");

        @BeforeSuite
        public static void deleteAllFilesFolder() {
            objOS_Version = new OS_Version();
            objSelectFolder = new SelectFolder();
            String s = objSelectFolder.folderName();
            if (objOS_Version.isUnix()) {

                String path = s + "screenshots/";
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
            driver.get(URL);
            //driver.manage().window().maximize();

        }

        @Test(description = "Проверка появление количества на иконке корзины")
        public void CheckDisplayCount1(){
            objHomePage = new HomePage(driver);
            objCatalog = new Catalog(driver);

            Assert.assertEquals(1,2);
        }


    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        objSelectFolder = new SelectFolder();
        String s = objSelectFolder.folderName();
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = s + "screenshots/" + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }
}
