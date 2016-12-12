import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 06.12.16.
 */
public class CabinetCart {

    WebDriver driver;

    public CabinetCart(WebDriver driver) {
        this.driver = driver;
    }

    By CartBtn = By.xpath(".//a[@class='icon__cart']");
    By HeaderTitel = By.xpath(".//h1[text()='Моя корзина']");
    By CheckboxSelectAll = By.xpath(".//label[contains(@data-bind,'ClickSelectAll')]");
    By AllChecboxes = By.xpath(".//label[contains(@data-bind,'Item')]");
    By MinusIcons = By.xpath(".//*[contains(@data-bind,'ClickMinus')]");
    By PlusIcons = By.xpath(".//*[contains(@data-bind,'ClickPlus')]");
    By InFavorites = By.xpath(".//div[@class='b-order-item__delay']/div/a");
    By AllRemoveIcons = By.xpath(".//*[contains(@data-bind,'ClickRemove')]");
    By Otlozit = By.xpath(".//span[text()='Отложить выбранные товары']");
    By DeleteSelected = By.xpath(".//span[text()='Удалить выбранные товары']");
    By ClearCart = By.xpath(".//span[text()='Очистить корзину']");
    By ContinueShop = By.xpath(".//a[text()='Продолжить покупки']");
    By Order = By.xpath(".//a[text()='Оформить заказ']");
    By Total = By.xpath(".//span[contains(@data-bind,'tatalForPayment')]");
    By AllItemsNames = By.xpath(".//a/span[contains(@data-bind,'full_name')]");
    By YesBtn = By.xpath(".//button[text()='Да']");
    By NoBtn = By.xpath(".//button[text()='Нет']");
    By CountItems = By.xpath(".//input[contains(@id,'ordered')]");
    By PriceItems = By.xpath("//span[@class='b-price']/span[contains(@data-bind,'endSum')]");
    By Sklads = By.xpath(".//span[contains(@data-bind,'count_reserve')]");
    By BarLoad = By.xpath("//div[@class='nanobar']/div[contains(@style,'width')]");
    By Success = By.xpath(".//h3[contains(@class,'icon-success')]");
    By EmptyCart = By.xpath(".//h1[text()='В корзине нет ни одного товара.']");


    String MaxMsg = "Достигнут максимум";
    String EmptyMsg = "В корзине нет ни одного товара.";
    String FavoritesMsg = "Выбранные товары добавлены в избранное.";


    public String GetSuccess(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Success));
        return driver.findElement(Success).getText();
    }

    public String GetFavoriteText(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(InFavorites));
        List<WebElement> AllFav = driver.findElements(InFavorites);
        return AllFav.get(i).getText();
    }

    public String GetEmptyMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmptyCart));
        return driver.findElement(EmptyCart).getText();
    }

    public int GetNaSklade(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Sklads));
        List<WebElement> AllSklads = driver.findElements(Sklads);
        return Integer.parseInt(AllSklads.get(i).getText());
    }


    public Double GetTotalPrice(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Total));
        return Double.parseDouble(driver.findElement(Total).getText());
    }
    public Double GetPriceIitem(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PriceItems));
        List<WebElement> AllPrices = driver.findElements(PriceItems);
        return Double.parseDouble(AllPrices.get(i).getText());
    }

    public Double GetCountIitem(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountItems));
        List<WebElement> AllCounts = driver.findElements(CountItems);
        return Double.parseDouble(AllCounts.get(i).getAttribute("value"));
    }

    public void InputMaxCountItem(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountItems));
        List<WebElement> AllCounts = driver.findElements(CountItems);
        String key = GetNaSklade(i) + 1 + "";
        AllCounts.get(i).sendKeys(key);
    }

    public void ClickPlusIconsForMax(int i) throws InterruptedException {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PlusIcons));
        List<WebElement> AllPlus = driver.findElements(PlusIcons);
        for (int x=0;x<GetNaSklade(i);x++){
            AllPlus.get(i).click();
            Thread.sleep(500);
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BarLoad));

        }

    }

    public void ClickCart(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CartBtn));
        driver.findElement(CartBtn).click();
    }

    public void ClickRemoveIcons(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllRemoveIcons));
        List<WebElement> AllRemoves = driver.findElements(AllRemoveIcons);
        AllRemoves.get(i).click();
    }

    public void ClickPlusIcons(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PlusIcons));
        List<WebElement> AllPlus = driver.findElements(PlusIcons);
        AllPlus.get(i).click();
    }

    public void ClickMinusIcons(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MinusIcons));
        List<WebElement> AllMinus = driver.findElements(MinusIcons);
        AllMinus.get(i).click();
    }

    public void ClickCheckbox(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllChecboxes));
        List<WebElement> AllCheckboxes = driver.findElements(AllChecboxes);
        AllCheckboxes.get(i).click();
    }

    public void ClickDeleteSelected(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(DeleteSelected));
        driver.findElement(DeleteSelected).click();
    }

    public void ClickYes(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(YesBtn));
        driver.findElement(YesBtn).click();
    }

    public void ClickNo(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NoBtn));
        driver.findElement(NoBtn).click();
    }


    public boolean CheckAppersNewName(String name){
        boolean y = false;

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllItemsNames));
        List<WebElement> AllNames = driver.findElements(AllItemsNames);
        for (WebElement x :AllNames){
            if (x.getText().equals(name))
            {
                y= true;
            }
        }

        return y;
    }

    public int CountItemsInCart(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllItemsNames));
        List<WebElement> AllNames = driver.findElements(AllItemsNames);
        System.out.println(AllNames.size());
        return AllNames.size();
    }

    public void ClearCartMethod(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ClearCart));
        driver.findElement(ClearCart).click();
        ClickYes();
    }

    public void ClickOtlozit(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Otlozit));
        driver.findElement(Otlozit).click();
    }

    public void ClickContinueShop(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ContinueShop));
        driver.findElement(ContinueShop).click();
    }

    public void ClickOrder(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Order));
        driver.findElement(Order).click();
    }



}
