import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 12.12.16.
 */
public class Order {

    WebDriver driver;
    HomePage objHomepage;

    public Order(WebDriver driver) {
        this.driver = driver;
    }

    By HeaderTitel = By.xpath(".//h1[contains(@data-bind,'orderList')]");
    By OrdersNames = By.xpath(".//a[contains(@data-bind,'ClickShow')]");
    By NameShop = By.xpath(".//td[contains(@data-bind,'name_shop')]");
    By Prices = By.xpath(".//span[contains(@data-bind,'final_cost')]");
    By Status = By.xpath(".//tr/td[4]/div[contains(@class,'status')]");
    By PaymentStatus = By.xpath(".//tr/td[5]/div[contains(@class,'status')]");
    By CopyToCart = By.xpath(".//div[contains(@data-bind,'ClickReturn')]");
    By Repeat = By.xpath(".//div[contains(@data-bind,'ClickRepeat')]");
    By Cancel = By.xpath(".//div[contains(@data-bind,'ClickCancel')]");
    By Edit = By.xpath(".//div[contains(@data-bind,'ClickEdit')]");
    By Delete = By.xpath(".//div[contains(@data-bind,'ClickDelete')]");
    By Yes = By.xpath(".//button[text()='Да']");
    By No = By.xpath(".//button[text()='Нет']");
    By Refresh = By.xpath(".//button[contains(@data-bind,'ClickRefresh')]");
    By Back = By.xpath(".//a[contains(@class,'btn__stepback')]");
    By Success = By.xpath(".//h3[contains(@class,'icon-success')]");

    String DeleteMsg = "Ваш заказ удален.";
    String CancelMsg = "Ваш заказ отменен.";
    String IncartMsg = "Ваш заказ скопирован в корзину.";
    String RepeatMsg = "Ваш заказ повторен.";


    public String GetSuccess(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Success));
        return driver.findElement(Success).getText();
    }

    public void CancelOrder(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Cancel));
        List<WebElement> CancelList = driver.findElements(Cancel);
        CancelList.get(i).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Yes));
        driver.findElement(Yes).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Success));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(Success));
    }

    public void DeleteOrder(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Delete));
        List<WebElement> DeleteList = driver.findElements(Delete);
        DeleteList.get(i).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Yes));
        driver.findElement(Yes).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Success));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(Success));
    }

    public void DeleteOrder1() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Delete));
        List<WebElement> DeleteList = driver.findElements(Delete);
        DeleteList.get(0).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Yes));
        driver.findElement(Yes).click();
    }

    public void CancelOrder1() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Cancel));
        List<WebElement> CancelList = driver.findElements(Cancel);
        CancelList.get(0).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Yes));
        driver.findElement(Yes).click();
    }

    public void ClickCopyToCart(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CopyToCart));
        List<WebElement> CopyToCartList = driver.findElements(CopyToCart);
        CopyToCartList.get(i).click();
    }

    public void ClickRepeat(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Repeat));
        List<WebElement> RepeatList = driver.findElements(Repeat);
        RepeatList.get(i).click();
    }

    public void ClickOrder(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(OrdersNames));
        List<WebElement> OrdersList = driver.findElements(OrdersNames);
        OrdersList.get(i).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(OrdersNames));
    }

    public void ClickEdit(int i) {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Edit));
        List<WebElement> EditList = driver.findElements(Edit);
        EditList.get(i).click();
    }

    public void ClickBackToList() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Back));
        driver.findElement(Back).click();
    }

    public void DeleteAllOrderMethod() throws InterruptedException {
        objHomepage = new HomePage(driver);
        objHomepage.ClickProfileIcon();
        objHomepage.ClickOrderBtn();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(HeaderTitel));
        while (driver.findElements(Cancel).size()>0){
            CancelOrder(0);
        }
        while (driver.findElements(Delete).size()>0){
            DeleteOrder(0);
        }
    }




}

