import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
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
 * Created by user on 12.12.16.
 */
public class OrderTest {
    static WebDriver driver;
    static OS_Version objOS_Version;
    HomePage objHomePage;
    Profile objProfile;
    Catalog objCatalog;
    Order objOrder;
    Oformit objOformit;
    CabinetCart objCabinetCart;
    static Waiters objWaiters;
    private static String URL=System.getProperty("url");



    @BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        if (objOS_Version.isUnix()) {
            String path = "/var/lib/jenkins/workspace/Тест личный кабинет (Заказы)/screenshots/";
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }


    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        objWaiters = new Waiters(driver);
        //driver.manage().window().maximize();
    }

    @Test(description = "Проверка добавления заказа и отображение в списке заказов")
    public void CheckAddOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOrder.OrdersNames));
    }

    @Test(description = "Проверка Открыть один из заказов и вернуться к списку заказов с помощью кнопки “Назад к списку заказов”")
    public void BackToOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickOrder(0);
        objOrder.ClickBackToList();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOrder.HeaderTitel));
    }



    @Test(description = "Проверка Открыть один из заказов на странице заказов в режиме редактирования")
    public void CheckEditOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickEdit(0);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.OformitTitel));
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.SelectAddressTitel));
    }

    @Test(description = "Проверка удаления заказов из списка")
    public void CheckDeleteOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.DeleteOrder1();
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.DeleteMsg);
    }

    @Test(description = "Проверка отмены заказов из списка")
    public void CheckCancelOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.CancelOrder1();
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.CancelMsg);
    }

    @Test(description = "Проверка копирование в корзину заказа из списка")
    public void CheckCopyToCart() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objCabinetCart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.CancelOrder(0);
        objOrder.ClickCopyToCart(0);
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.IncartMsg);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinetCart.HeaderTitel));
    }

    @Test(description = "Проверка повторения заказа из списка")
    public void CheckRepeat() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.CancelOrder(0);
        objOrder.ClickRepeat(0);
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.RepeatMsg);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.SelectAddressTitel));
    }

    @Test(description = "Проверка удаления из открытого заказа")
    public void CheckDeleteOpensOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickOrder(0);
        objOrder.DeleteOrder1();
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.DeleteMsg);
    }

    @Test(description = "Проверка отмены из открытого заказа")
    public void CheckCancelOpensOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickOrder(0);
        objOrder.CancelOrder1();
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.CancelMsg);
    }


    @Test(description = "Проверка копирования в корзину из открытого заказа")
    public void CheckCopyToCartOpensOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objCabinetCart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickOrder(0);
        objOrder.ClickCopyToCart(0);
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.IncartMsg);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objCabinetCart.HeaderTitel));
    }

    @Test(description = "Проверка редактирования из открытого заказа")
    public void CheckEditOpensOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objCabinetCart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.ClickOrder(0);
        objOrder.ClickEdit(0);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.OformitTitel));
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.SelectAddressTitel));
    }

    @Test(description = "Проверка повторения из открытого заказа")
    public void CheckRepeatOpensOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objOrder = new Order(driver);
        objOformit = new Oformit(driver);
        objCabinetCart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objOrder.DeleteAllOrderMethod();
        objHomePage.LogoText_RetunToHomepage();
        objHomePage.DeleteAllCartMethod();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        objCatalog.AddRandomItem();
        objHomePage.ClickCartIcon();
        objHomePage.ClickOformit();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickOrderBtn();
        objOrder.CancelOrder(0);
        objOrder.ClickOrder(0);
        objOrder.ClickRepeat(0);
        Assert.assertEquals(objOrder.GetSuccess(),objOrder.RepeatMsg);
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.OformitTitel));
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objOformit.SelectAddressTitel));
    }
















    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = "/var/lib/jenkins/workspace/Тест личный кабинет (Заказы)/screenshots/" + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }

}
