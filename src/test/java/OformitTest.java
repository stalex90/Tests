import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Администратор on 23.01.2017.
 */
public class OformitTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    static HomePage objHomePage;
    static Oformit objOformit;
    static Catalog objCatalog;
    static Waiters objWaiters;
    static Order objOrder;

    /*@BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        if (objOS_Version.isUnix()) {
            String path = "/var/lib/jenkins/workspace/Тест личный кабинет (Сообщения)/screenshots/";
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }*/

    @BeforeMethod
    public static void openBrowser() throws InterruptedException{
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();
        objOformit = new Oformit(driver);
        objHomePage = new HomePage(driver);
        objWaiters = new Waiters(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.AddItem(1);
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
    }

    @Test(description = "Оформить заказ")
    public void CheckCompleteOrder()  {
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickShipCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickPayCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickConfirmOrder();
        Assert.assertEquals(objOformit.GetSuccessMsg(),objOformit.succesMsg);
    }

    @Test(description = "Добавить новый адрес")
    public void CheckAddAddress() {
        objOformit.clickAddAddress();
        objOformit.inputAllAddress();
        Assert.assertEquals(objOformit.GetSuccessMsg(),objOformit.succesAdrMsg);
    }

    @Test(description = "Вернуться назад")
    public void CheckBack(){
        objOformit.clickBackBtn();
        objHomePage.ClickCartIcon();
        Assert.assertTrue(objWaiters.isElementPresent(objHomePage.Oformit));
    }

    @Test(description = "Пустой адрес")
    public void CheckErrorAddress(){
        objOformit.clickContinueBtn();
        Assert.assertEquals(objOformit.GetErrorMsg(),objOformit.errorAdr);
    }

    @Test(description = "Пустой способ доставки")
    public void CheckErrorSposob(){
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickContinueBtn();
        Assert.assertEquals(objOformit.GetErrorMsg(),objOformit.errorSposob);
    }

    @Test(description = "Пустой способ платежа")
    public void CheckErrorPay() throws InterruptedException {
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        Thread.sleep(3000);
        objOformit.clickShipCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        Thread.sleep(3000);
        objOformit.clickContinueBtn();
        Assert.assertEquals(objOformit.GetErrorMsg(),objOformit.errorPay);
    }

    @Test(description = "Отмена заказа")
    public void CheckCancelOrder() {
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickShipCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickPayCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickCancelOrder();
        objOformit.clickYes();
        Assert.assertEquals(objOformit.GetSuccessMsg(),objOformit.cancelMsg);
    }

    @Test(description = "Оплатить заказ")
    public void CheckPayOrder()  {
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickShipCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickPayCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickConfirmOrder();
        objOformit.clickPayBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.payHeader));
    }

    @Test(description = "Обновить заказ")
    public void CheckRefreshOrder()  {
        objOformit.clickaddressCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickShipCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickPayCheckboxes(0);
        objOformit.clickContinueBtnInvis();
        objOformit.clickConfirmOrder();
        objOformit.clickRefreshBtn();
    }











    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = "/var/lib/jenkins/workspace/Оформление заказа/screenshots/" + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }


}
