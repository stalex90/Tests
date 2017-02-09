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
 * Created by user on 05.12.16.
 */
public class FavoritesTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    static HomePage objHomePage;
    static Favorites objFavorites;
    static Catalog objCatalog;
    static Oformit objOformit;
    private static String URL=System.getProperty("url");
    static SelectFolder objSelectFolder;

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

    @Test(description = "Проверка добавление в избранное и появление в списке избранного")
    public void AddFavorite() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objFavorites =new Favorites(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClearAllFavoritesMethod();
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
        objFavorites.ClickYes();
        Assert.assertEquals(objFavorites.GetSuccessMsg(),objFavorites.SuccessDeleteMsg);

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
