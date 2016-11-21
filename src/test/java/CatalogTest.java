import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 31.10.16.
 */
public class CatalogTest {

    static WebDriver driver;
    HomePage objHomePage;
    LoginPage objLoginPage;
    Catalog objCatalog;
    Cart objCart;
    Oformit objOformit;
    static OS_Version objOS_Version;

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();

    }

    @Test(description = "Проверка появление количества на иконке корзины")
    public void CheckDisplayCount(){
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(10);
        Assert.assertTrue(driver.findElement(objHomePage.CartCount).isDisplayed());
    }

    @Test(description = "Добавление в корзину 8 элементов и Сравнить количество товаров в корзине и количество на иконке корзины")
    public void CheckCartCountandCountItem()  {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.AddFirst8Item();
        objHomePage.ClickCartIcon();
        Assert.assertEquals(objHomePage.GetCartCount(),objHomePage.GetItemsCount());
    }


    @Test(description = "Проверить удаление товара")
    public void CheckDelete()  {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.AddFirst8Item();
        objHomePage.ClickCartIcon();
        objHomePage.DeleteItem(3);
        Assert.assertEquals(objHomePage.GetCartCount(),7);
    }

    @Test (description = "Добавить количество для товара")
    public void CheckAddItemCount(){
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(7);
        objHomePage.ClickCartIcon();
        objHomePage.ItemCountAdd(1);
        Assert.assertEquals(objHomePage.GetItemCount(1),2);
    }

    @Test (description = "Убавить количество для товара")
    public void CheckRemoveItemCount(){
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(7);
        objHomePage.ClickCartIcon();
        objHomePage.ItemCountRemove(1);
        Assert.assertEquals(objHomePage.GetItemCount(1),0);
    }

    @Test(description = "Сложить сумму всех товаров и проверить общую сумму")
    public void CheckFinalCost()  {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.AddFirst8Item();
        objHomePage.ClickCartIcon();
        Assert.assertEquals(objHomePage.GetSummFinalCost(),objHomePage.GetFinalCost());
    }

    @Test (description = "Перейти в корзину")
    public void CheckInCart() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(7);
        objHomePage.ClickCartIcon();
        objCart = objHomePage.ClickInCart();
        Thread.sleep(15000);
        Assert.assertTrue(driver.findElement(objCart.CartTitel).isDisplayed());
    }

    @Test (description = "Перейти к оформлению")
    public void CheckOformlenie() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objLoginPage = new LoginPage(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(7);
        objHomePage.ClickCartIcon();
        objOformit = objHomePage.ClickOformit();
        objLoginPage.InputLogin();
        objLoginPage.InputPassword();
        objLoginPage.ClickLoginBtn();
        Thread.sleep(15000);
        Assert.assertTrue(driver.findElement(objOformit.OformitTitel).isDisplayed());
    }

    @Test (description = "Убавить количество для товара")
    public void CheckNameCart(){
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.SelectCategory("Кухонные ножи");
        objCatalog.AddItem(7);
        objHomePage.ClickCartIcon();
        Assert.assertEquals(objHomePage.GetCartName(),"Корзина");
    }

    @Test(description = "Удалить 1 товар и сложить сумму всех товаров и проверить общую сумму")
    public void CheckFinalCostAfterRemove()  {
        objHomePage = new HomePage(driver);
        objCatalog = new Catalog(driver);
        objCatalog.AddFirst8Item();
        objHomePage.ClickCartIcon();
        objHomePage.DeleteItem(2);
        Assert.assertEquals(objHomePage.GetSummFinalCost(),objHomePage.GetFinalCost());
    }



















    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(13000);
        driver.quit();
    }


}
