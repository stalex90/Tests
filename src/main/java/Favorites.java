import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 28.11.16.
 */
public class Favorites {

    WebDriver driver;
    HomePage objHomepage;


    String SuccessDeleteMsg = "Выбранные товары удалены из избранного.";
    String SuccessInCartMsg = "Товар успешно добавлен в корзину";

    public Favorites(WebDriver driver) {
        this.driver = driver;
    }

    By Favorites = By.xpath(".//a[@class='icon__favorites']");
    By Support = By.xpath(".//span[@data-target='support']");
    By CheckboxAll = By.xpath(".//label[contains(@data-bind,'SelectAll')]");
    By Delete = By.xpath(".//span[contains(@data-bind,'Remove')]");
    By InCartBtn = By.xpath(".//button[contains(@class,'btn-sm')][1]");
    By BuyNow = By.xpath(".//button[contains(@class,'btn-sm')][2]");
    By DeleteFromFavor = By.xpath(".//span[text()='Удалить из избранного']");
    By ClearAll = By.xpath(".//span[text()='Очистить избранное от товаров']");
    By AllFavoritesName = By.xpath(".//div[@class='b-order-item__name']/a/span");
    By YesBtn = By.xpath(".//button[text()='Да']");
    By NoBtn = By.xpath(".//button[text()='Нет']");
    By SuccesMsg = By.xpath(".//h3[contains(@class,'icon-success')]");

    public void ClickSelectAllCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CheckboxAll));
        driver.findElement(CheckboxAll).click();
    }

    public void ClickDeleteFromFavor(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(DeleteFromFavor));
        driver.findElement(DeleteFromFavor).click();
    }



    public void ClickInCart(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(InCartBtn));
        driver.findElement(InCartBtn).click();
    }

    public void ClickBuyNow(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BuyNow));
        driver.findElement(BuyNow).click();
    }

    public String GetSuccessMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SuccesMsg));
        return driver.findElement(SuccesMsg).getText();
    }
    public void ClickFavorites(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Favorites));
        driver.findElement(Favorites).click();
    }

    public boolean CheckAppersNewName(String name){
        boolean y = false;

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllFavoritesName));
        List<WebElement> AllNames = driver.findElements(AllFavoritesName);
        for (WebElement x :AllNames){
            if (x.getText().equals(name))
            {
                y= true;
            }
        }

       return y;
    }

    public void ClickClearAll(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ClearAll));
        driver.findElement(ClearAll).click();
    }
    public void ClickYes(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(YesBtn));
        driver.findElement(YesBtn).click();
    }

    public void ClickDelete(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Delete));
        List<WebElement> DeleteList = driver.findElements(Delete);
        DeleteList.get(i).click();
    }

    public void ClearAllMethod(){
        objHomepage = new HomePage(driver);
        objHomepage.ClickProfileIcon();
        objHomepage.ClickProfileBtn();
        ClickFavorites();

            if (driver.findElements(ClearAll).size() > 0) {
                ClickClearAll();
                ClickYes();
            }

        objHomepage.LogoText_RetunToHomepage();
    }

}
