import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 05.12.16.
 */
public class FavoritesTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    static HomePage objHomePage;
    static Favorites objFavorites;
    static Catalog objCatalog;
    static Oformit objOformit;


    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();
    }

    @Test(description = "Проверка добавление в избранное и появление в списке избранного")
    public void AddFavorite() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        String SelectedItemName = objCatalog.GetName();
        objCatalog.OpenRandomItem();
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        Assert.assertTrue(objFavorites.CheckAppersNewName(SelectedItemName));
    }

    @Test(description = "Проверка удаления 1 элемента из избранного")
    public void DeleteOneItem() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.OpenItem(0);
        objCatalog.AddFavorite();
        objCatalog.NavigateBack();
        objCatalog.OpenItem(1);
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickDelete(0);
        objFavorites.ClickYes();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessDeleteMsg);
    }

    @Test(description = "Проверка добавления из избранного в корзину")
    public void AddInCart() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.OpenRandomItem();
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickInCart();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessInCartMsg);
    }

    @Test(description = "Проверка Очистить избранное от товаров")
    public void CheckClearAll() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.OpenItem(0);
        objCatalog.AddFavorite();
        objCatalog.NavigateBack();
        objCatalog.OpenItem(1);
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickClearAll();
        objFavorites.ClickYes();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessDeleteMsg);
    }

    @Test(description = "Проверка начала оформления из избранного")
    public void CheckBuyNow() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.OpenRandomItem();
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickBuyNow();
        Assert.assertTrue(driver.findElement(objOformit.OformitTitel).isDisplayed());

    }

    @Test(description = "Проверка работы удалить из избранного")
    public void CheckDeleteFromFavor() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objOformit = new Oformit(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllMethod();
        objCatalog.SelectCategory(objCatalog.NeedCategory);
        objCatalog.OpenItem(0);
        objCatalog.AddFavorite();
        objCatalog.NavigateBack();
        objCatalog.OpenItem(1);
        objCatalog.AddFavorite();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objFavorites.ClickFavorites();
        objFavorites.ClickSelectAllCheckbox();
        objFavorites.ClickDeleteFromFavor();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessDeleteMsg);

    }


    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
