import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.util.List;

/**
 * Created by user on 31.10.16.
 */
public class Catalog {
    WebDriver driver;
    String NeedCategory = "Зонты";
    int index = 0 + (int)(Math.random() * ((19 - 0) + 1));


    public Catalog(WebDriver driver) {
        this.driver = driver;
    }

    By AllCategorys = By.xpath(".//*[@id='catalogWidgetId']/aside/div/ul/li/u/span");
    By FirstItem = By.xpath(".//*[@id='content_pkp']/div/div/div[2]/div[1]/div/div[5]");
    By AllItemsOnPage = By.xpath(".//div[@class='b-catalog__item']");
    By SuccessMsg = By.xpath(".//h3[text()='Товар успешно добавлен в корзину']");
    By CategoryTitle = By.id("js-nosidebar");
    By FavoriteBtn = By.xpath(".//a[@class='pseudo-link']");
    By AllItemsTitel = By.xpath(".//div[@class='b-item with_popover']/div/div/h4[@class='b-item__title']/a");
    By SuccessFavoriteMsg = By.xpath(".//h3[contains(@class,'icon-success')]");

    String SuccessFavorite = "Выбранные товары добавлены в избранное.";


    public void SelectCategory(String name)
    {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllCategorys));
        List<WebElement> AllCategoryList = driver.findElements(AllCategorys);
        for (WebElement x : AllCategoryList) {
            if (x.getText().equals(name)) {
                x.click();
                return;
            }
        }
    }

    public void AddItem(int i){

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsOnPage);
        Actions action = new Actions(driver);
        action.moveToElement(ItemsList.get(i)).release().build().perform();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content_pkp']/div/div/div[2]/div[" + (i+1) + "]/div/div[5]/div[1]/div[3]/button")));
        driver.findElement(By.xpath(".//*[@id='content_pkp']/div/div/div[2]/div[" + (i+1) + "]/div/div[5]/div[1]/div[3]/button")).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SuccessMsg));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SuccessMsg));
    }

    public void AddFirst8Item() {
        SelectCategory(NeedCategory);

        for (int x=0;x<8;x++){
            AddItem(x);
        }
    }

    public void OpenItem(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsTitel);
        ItemsList.get(i).click();
    }

    public void NavigateBack(){
        driver.navigate().back();
    }


    public void OpenRandomItem(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsTitel);
        ItemsList.get(index).click();
    }

    public String GetName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsTitel);
        return ItemsList.get(index).getText();
    }

    public void AddFavorite(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FavoriteBtn));
        driver.findElement(FavoriteBtn).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SuccessFavoriteMsg));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SuccessFavoriteMsg));
    }

    public String GetFavoriMsg(){
        return driver.findElement(SuccessFavoriteMsg).getText();
    }


}
