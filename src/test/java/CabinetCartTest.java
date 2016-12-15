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
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 06.12.16.
 */
public class CabinetCartTest {

    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    Catalog objCatalog;
    Cart objCart;
    Oformit objOformit;
    Favorites objFavorites;
    CabinetCart objCabinetcart;
    static OS_Version objOS_Version;

    /*@BeforeSuite
    public static void deleteAllFilesFolder() {
        String path = "/var/lib/jenkins/workspace/Тест личный кабинет (Корзина)/src/test/resources/";
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }*/

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();

    }

    @Test(description = "Проверка добавления в коризну из карточек товара и отображения добавленного в корзине ЛК")
    public void CheckAddFromCard() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        String SelectedItemName = objCatalog.GetName();
        objCatalog.OpenRandomItem();
        objCatalog.ClickInCart();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        Assert.assertTrue(objCabinetcart.CheckAppersNewName(SelectedItemName));
    }

    @Test(description = "Проверка добавления в коризну из каталога и отображения добавленного в корзине ЛК")
    public void CheckAddFromCatalog() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        String SelectedItemName = objCatalog.GetName();
        objCatalog.AddRandomItem();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        Assert.assertTrue(objCabinetcart.CheckAppersNewName(SelectedItemName));
    }

    @Test(description = "Проверка добавления из избранного в корзину")
    public void CheckAddFromFavorites() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objFavorites.ClearAllFavoritesMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory2);
        String SelectedItemName = objCatalog.GetName();
        objCatalog.OpenRandomItem();
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickInCart();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessInCartMsg);
        objCabinetcart.ClickCart();
        Assert.assertTrue(objCabinetcart.CheckAppersNewName(SelectedItemName));
    }

    @Test(description = "Проверка отметить несколько товаров и удалить из Корзины кнопкой “Удалить выбранные товары”")
    public void CheckDelete() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(5, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        int CartCount = objCabinetcart.CountItemsInCart();
        objCabinetcart.ClickCheckbox(0);
        objCabinetcart.ClickCheckbox(2);
        objCabinetcart.ClickDeleteSelected();
        objCabinetcart.ClickYes();
        Assert.assertEquals(CartCount-2,objCabinetcart.CountItemsInCart());
    }

    @Test(description = "Проверка Удалить несколько  товаров по одному с помощью кнопки “Удалить”")
    public void CheckDeleteFor1() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(5, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        int CartCount = objCabinetcart.CountItemsInCart();
        objCabinetcart.ClickRemoveIcons(0);
        objCabinetcart.ClickYes();
        objCabinetcart.ClickRemoveIcons(1);
        objCabinetcart.ClickYes();
        Assert.assertEquals(CartCount-2,objCabinetcart.CountItemsInCart());
    }

    @Test(description = "Проверка Увеличить количество одного из товаров с помощью кнопки +")
    public void CheckPlusItem() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickPlusIcons(0);
        Double a = objCabinetcart.GetTotalPrice();
        Double b = objCabinetcart.GetPriceIitem(0)*objCabinetcart.GetCountIitem(0);
        try {
            Assert.assertEquals(a,b);
        }
        catch (AssertionError e){
        objCabinetcart.ClickMinusIcons(0);
        Assert.assertEquals(a,b);
        }
    }

    @Test(description = "Проверка Увеличить количество одного из товаров до максимального с помощью кнопки +")
    public void CheckMax() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickPlusIconsForMax(0);
        String a = objCabinetcart.GetSuccess();
        String b = objCabinetcart.MaxMsg;
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBe(objHomePage.CartCount,objCabinetcart.GetNaSklade(0)+""));
        objCabinetcart.ClearCartMethod();
        Assert.assertEquals(a, b);
    }

    @Test(description = "Уменьшить количество одного из товаров с помощью кнопки -")
    public void CheckMinusItem() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickCartIcon();
        objHomePage.ItemCountAdd(0);
        objHomePage.ItemCountAdd(0);
        objHomePage.ItemCountAdd(0);
        objHomePage.ClickCartIcon();
        Thread.sleep(3000);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickMinusIcons(0);
        Double a = objCabinetcart.GetTotalPrice();
        Double b = objCabinetcart.GetPriceIitem(0)*objCabinetcart.GetCountIitem(0);
        objCabinetcart.ClearCartMethod();
        Assert.assertEquals(a,b);

    }

    @Test(description = "Проверка Увеличить количество одного из товаров в ручную до максимума + 1")
    public void CheckMaxHandInput() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.InputMaxCountItem(0);
        Assert.assertEquals(objCabinetcart.GetSuccess(),objCabinetcart.MaxMsg);
        objCabinetcart.ClearCartMethod();
    }

    @Test(description = "Очистить корзину с помощью кнопки “Очистить корзину”")
    public void CheckClearCart() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClearCartMethod();
        Thread.sleep(2000);
        objCabinetcart.ClickCart();
        Assert.assertEquals(objCabinetcart.GetEmptyMsg(),objCabinetcart.EmptyMsg);
    }

    @Test(description = "Проверка Выделить несколько товаров, отметить и отправить в Избранное с помощью кнопки “Отложить выбранные товары”")
    public void CheckInFavorites() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objFavorites = new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objFavorites.ClearAllFavoritesMethod();
        objCatalog.AddFirstIitems(5, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickCheckbox(0);
        objCabinetcart.ClickCheckbox(1);
        objCabinetcart.ClickOtlozit();
        Assert.assertEquals(objCabinetcart.GetSuccess(),objCabinetcart.FavoritesMsg);
        Assert.assertEquals(objCabinetcart.GetFavoriteText(3),"В избранное");
        Assert.assertEquals(objCabinetcart.GetFavoriteText(2),"В избранное");
        Assert.assertEquals(objCabinetcart.GetFavoriteText(1),"Избранный товар");
        Assert.assertEquals(objCabinetcart.GetFavoriteText(0),"Избранный товар");
    }

    @Test(description = "Проверка Перейти обратно в магазин с помощью кнопки “Продолжить покупки”")
    public void CheckContinueShopping() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objFavorites = new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickContinueShop();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(objCatalog.AllCategorys));
        Assert.assertTrue(driver.findElement(objCatalog.AllCategorys).isDisplayed());
    }

    @Test(description = "Перейти к оформлению покупок с помощью кнопки “Оформить заказ”")
    public void CheckOrder() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCabinetcart = new CabinetCart(driver);
        objFavorites = new Favorites(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        Thread.sleep(2000);
        objHomePage.DeleteAllCartMethod();
        objCatalog.AddFirstIitems(2, objCatalog.NeedCategory2);
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objCabinetcart.ClickCart();
        objCabinetcart.ClickOrder();
        Assert.assertTrue(driver.findElement(objOformit.OformitTitel).isDisplayed());
    }

    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "/var/lib/jenkins/workspace/Тест личный кабинет (Корзина)/src/test/resources/" + testResult.getName() + ".jpg";
            FileUtils.copyFile(scrFile, new File(path));
        }
        driver.quit();
    }
}
