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
 * Created by user on 26.12.16.
 */
public class SearchTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    static Search objSearch;
    static Waiters objWaiters;
    static Catalog objCatalog;
    static HomePage objHomepage;

   /* @BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        if (objOS_Version.isUnix()) {
            String path = "/var/lib/jenkins/workspace/Тест поиска/screenshots/";
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }*/

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();
        objSearch = new Search(driver);
        objWaiters = new Waiters(driver);
        objCatalog = new Catalog(driver);


    }

    @Test(description = "Ввести слово и произвести поиск")
    public void checkSearch(){
        objSearch.inputSearchFld("Зонт");
        objSearch.clickSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "Ввести слово, выбрать категорию и произвести поиск")
    public void checkSearchwithCategory(){
        objSearch.inputSearchFld("Зонт");
        objSearch.clickCategory();
        objSearch.selectCategory("Зонты");
        objSearch.clickSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "Произвести пустой поиск")
    public void checkEmptySearch(){
        objSearch.clickSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.success));
        Assert.assertEquals(objSearch.getSucces(),objSearch.succesMsg);
    }

    @Test(description = "Произвести некорректный поиск")
    public void checkIncorrectSearch(){
        objSearch.inputSearchFld("1234567890");
        objSearch.clickSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertEquals(objSearch.getTitelResult(),objSearch.emptytitelResult);
    }

    @Test(description = "Расширенный поиск по ключевому слову")
    public void checkAdvancedSearch(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "расширенный поиск по ключевому  слову с выбором категории")
    public void checkAdvancedSearchwithCategory(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.clickAdvancedCategory();
        objSearch.selectAdvancedCategory("Зонты");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "расширенный поиск с сортировкой по цене")
    public void checkAdvancedSearchwithPrice(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.inputMinPrice("1000");
        objSearch.inputMaxPrice("1100");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.checkPrices("1000","1100"));
    }

    @Test(description = "расширенный поиск с выбором продавца")
    public void checkAdvancedSearchwithSeller(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.clickAdvacedSeller();
        objSearch.selectSeller("Тестовая компания 1");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "расширенный поиск с исключающим словом")
    public void checkAdvancedSearchwithExceptword(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.inputExceptWord("Три");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
    }

    @Test(description = "расширенный поиск с выбором категории, продавца, ключевого слова и цены")
    public void checkAdvancedSearchFull(){
        objSearch.clickAdvancedSearch();
        objSearch.inputKeyWord("Зонт");
        objSearch.inputMinPrice("1000");
        objSearch.inputMaxPrice("1100");
        objSearch.clickAdvacedSeller();
        objSearch.selectSeller("Тестовая компания 1");
        objSearch.clickAdvancedCategory();
        objSearch.selectAdvancedCategory("Зонты");
        objSearch.clickAdvancedSearchBtn();
        Assert.assertTrue(objWaiters.isElementPresentWaiters(objSearch.titelResult));
        Assert.assertTrue(objCatalog.getAllItemsOnPageCount()>0);
        Assert.assertTrue(objCatalog.checkPrices("1000","1100"));
    }
































    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = "/var/lib/jenkins/workspace/Тест поиска/screenshots/" + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }
}
