import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
 * Created by user on 19.12.16.
 */
public class MessageTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    HomePage objHomePage;
    Profile objProfile;
    Messages objMessages;
    private static String URL=System.getProperty("url");
    static SelectFolder objSelectFolder;
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
    public static void openBrowser(){
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (objOS_Version.isUnix()) {
            driver.get(URL);}
        if (objOS_Version.isWindows()){
            driver.get("http://pokupotest.pokupo.ru/shop/1");
        }
        objWaiters = new Waiters(driver);

    }

    @Test(description = "Создать новое сообщение одному из существующих  продавцов.")
    public void CheckNewMsg() {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.DeleteAllMessages();
        objMessages.ClickNewMsg();
        objMessages.InputAdresat();
        objMessages.InputThema();
        objMessages.InputText();
        objMessages.ClickSend();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(objMessages.SendBtn));
        Assert.assertTrue(driver.findElements(objMessages.MessagePhoto).size()>0);
    }

    @Test(description = "Удалить все сообщения c помощью чекбокса выбрать все")
    public void DeleteAllMsg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.Send1Messge();
        driver.navigate().refresh();
        objMessages.ClickSelectAll();
        objMessages.ClickDeleteSelected();
        objMessages.ClickYes();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objMessages.NoMsgs));
    }

    @Test(description = "Удалить сообщение с помощью иконки корзина")
    public void Delete1Msg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.DeleteAllMessages();
        objMessages.Send1Messge();
        driver.navigate().refresh();
        objMessages.ClickDelete(0);
        objMessages.ClickYes();
        Assert.assertTrue(driver.findElement(objMessages.NoMsgs).isDisplayed());
    }

    @Test(description = "Проверка появления ошибки пустых полей")
    public void CheckEmptyMsg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.ClickNewMsg();
        objMessages.ClickSend();
        Assert.assertEquals(objMessages.GetTextError(),objMessages.EmptyMsg);
        Assert.assertEquals(objMessages.GetAdresatError(),objMessages.EmptyMsg);
        Assert.assertEquals(objMessages.GetThemaError(),objMessages.EmptyMsg);
    }

    @Test(description = "Проверка появления ошибки пустых полей")
    public void CheckIncorrectAdesatMsg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.ClickNewMsg();
        objMessages.InputIncorrectAdresat();
        objMessages.ClickSend();
        Assert.assertEquals(objMessages.GetAdresatError(),objMessages.IncorrectAdresatMsg);

    }

    @Test(description = "Проверка отправки неверному адресату")
    public void CheckSendIncorrectAdesat() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.DeleteAllMessages();
        objMessages.ClickNewMsg();
        objMessages.InputIncorrectAdresat();
        objMessages.InputThema();
        objMessages.InputText();
        objMessages.ClickSend();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(objMessages.NoMsgs).isDisplayed());
    }

    @Test(description = "Открыть сообщение и удалить сообщение с помощью иконки корзина")
    public void DeleteOpenMsg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.DeleteAllMessages();
        objMessages.Send1Messge();
        objMessages.ClickMessagePhoto(0);
        objMessages.ClickDelete(0);
        objMessages.ClickYes();
        Assert.assertTrue(driver.findElement(objMessages.NoMsgs).isDisplayed());
    }

    @Test(description = "Ответить на сообщение")
    public void ReSendMsg() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objMessages = new Messages(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickMessageBtn();
        objMessages.DeleteAllMessages();
        objMessages.Send1Messge2();
        objMessages.ClickMessagePhoto(0);
        objMessages.InputReText();
        objMessages.ClickSend();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElements(objMessages.ReMessagePhoto).size()==2);

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
