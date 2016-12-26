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
    String NeedCategory2 = "Средства по уходу за кожей лица Крема MIZON";
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
    By AllItemsPrice = By.xpath(".//div[@class='b-catalog']/div[@class='b-catalog__items cards']/div/div/div/div/div/span[1]");
    By SuccessFavoriteMsg = By.xpath(".//h3[contains(@class,'icon-success')]");
    By InCart = By.xpath(".//*[@id='btn_to_cart']");

    String SuccessFavorite = "Выбранные товары добавлены в избранное.";

    public int getAllItemsOnPageCount(){
        List<WebElement> All = driver.findElements(AllItemsPrice);
        return All.size();
    }


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

    public void ClickInCart(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(InCart));
        driver.findElement(InCart).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SuccessMsg));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SuccessMsg));
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

    public void AddFirstIitems(int i,String s) {
        SelectCategory(s);

        for (int x=1;x<i;x++){
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

    public void AddRandomItem(){

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsOnPage);
        Actions action = new Actions(driver);
        action.moveToElement(ItemsList.get(index)).release().build().perform();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='content_pkp']/div/div/div[2]/div[" + (index+1) + "]/div/div[5]/div[1]/div[3]/button")));
        driver.findElement(By.xpath(".//*[@id='content_pkp']/div/div/div[2]/div[" + (index+1) + "]/div/div[5]/div[1]/div[3]/button")).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SuccessMsg));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SuccessMsg));
    }

    public String GetName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CategoryTitle));
        List<WebElement> ItemsList = driver.findElements(AllItemsTitel);
        System.out.println(ItemsList.get(index).getText());
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

    public boolean checkPrices(String min1, String max1){
        int min = Integer.parseInt(min1);
        int max = Integer.parseInt(max1);
        List<WebElement> ItemsList = driver.findElements(AllItemsPrice);
        boolean a = true;
        for (int i = ItemsList.size()-1; i > 0; i--){
            if (Integer.parseInt(ItemsList.get(i).getText())< min || Integer.parseInt(ItemsList.get(i).getText()) > max){
                a = false;
            }
        }
        return a;

    }


}
