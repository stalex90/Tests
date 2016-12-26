import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 26.12.16.
 */
public class Search {
    WebDriver driver;
    public Search(WebDriver driver) {
        this.driver = driver;}

    public void Waiter(By element){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    By searchFld = By.xpath(".//*[@class='b-search__input']");
    By category = By.xpath(".//*[contains(@class,'search_dropdown__trigger')]");
    By categoryItems = By.xpath("//a[contains(@data-bind,'ClickItem')]");
    By searchBtn = By.xpath(".//*[@class='b-search__button']");
    By advancedSearch = By.xpath(".//*[@id='advansed_search_form_slidedown']");
    By advancedCategory = By.xpath(".//*[@id='advansed_search_form__category_dropdown__trigger']");
    By advancedCategoryArrow = By.xpath("//span[@class='fancytree-expander']");
    By advancedCategoryItems= By.xpath("//*[@class='fancytree-title']");
    By advancedCategoryCheckbox = By.xpath("//*[@class='fancytree-checkbox']");
    By advancedSeller = By.xpath(".//*[@id='typeSeller_chosen']/a");
    By advancedSellerItems = By.xpath("//ul[@class='chosen-results']/li");
    By keyWords = By.xpath("//*[@name='keyWords']");
    By exceptWords = By.xpath("//*[@name='exceptWords']");
    By anyWord = By.xpath(".//*[@id='typeSearch_chosen']/a");
    By minPrice = By.xpath(".//*[@id='minPrice']");
    By maxPrice = By.xpath(".//*[@id='maxPrice']");
    By advancedSearchBtn = By.xpath("//*[@class='btn btn-block b-advanced-search__submit']");
    By titelResult = By.xpath("//*[@id='js-nosidebar']");
    By success = By.xpath(".//h3[contains(@class,'icon-success')]");

    String succesMsg = "Введите название товара для его поиска.";
    String emptytitelResult = "По вашему запросу товаров не найдено";

    public String getSucces(){
        Waiter(success);
        return driver.findElement(success).getText();
    }

    public String getTitelResult(){
        Waiter(titelResult);
        return driver.findElement(titelResult).getText();
    }

    public void inputSearchFld(String word){
        Waiter(searchFld);
        driver.findElement(searchFld).sendKeys(word);
    }

    public void clickCategory(){
        Waiter(category);
        driver.findElement(category).click();
    }

    public void selectCategory(String word){
        Waiter(categoryItems);
        List<WebElement> ItemsList = driver.findElements(categoryItems);
        for (WebElement x : ItemsList){
            if (x.getText().equals(word)){
                x.click();
            }
        }
    }

    public void clickAdvacedSeller(){
        Waiter(advancedSeller);
        driver.findElement(advancedSeller).click();
    }

    public void selectSeller(String word){
        Waiter(advancedSellerItems);
        List<WebElement> ItemsList = driver.findElements(advancedSellerItems);
        for (WebElement x : ItemsList){
            if (x.getText().equals(word)){
                x.click();
            }
        }
    }

    public void selectAdvancedCategory(String word){
        int k;
        Waiter(advancedCategoryArrow);
        clickAdvancedCategoryArrow();
        List<WebElement> AllItems = driver.findElements(advancedCategoryItems);
        List<WebElement> AllCheckboxes = driver.findElements(advancedCategoryCheckbox);

        for (WebElement x : AllItems){
            if (x.getText().equals(word)){
                k = AllItems.indexOf(x);
                AllCheckboxes.get(k).click();
            }
        }

    }

    public void clickAdvancedSearch(){
        Waiter(advancedSearch);
        driver.findElement(advancedSearch).click();
    }

    public void clickAdvancedCategory(){
        Waiter(advancedCategory);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(advancedCategory),5,5).click().release().build().perform();
    }

    public void clickAdvancedCategoryArrow(){
        Waiter(advancedCategoryArrow);
        driver.findElement(advancedCategoryArrow).click();
    }

    public void clickSearchBtn(){
        Waiter(searchBtn);
        driver.findElement(searchBtn).click();
    }

    public void inputKeyWord(String word){
        Waiter(keyWords);
        driver.findElement(keyWords).sendKeys(word);
    }

    public void inputExceptWord(String word){
        Waiter(exceptWords);
        driver.findElement(exceptWords).sendKeys(word);
    }

    public void inputMinPrice(String word){
        Waiter(minPrice);
        driver.findElement(minPrice).sendKeys(word);
    }

    public void inputMaxPrice(String word){
        Waiter(maxPrice);
        driver.findElement(maxPrice).sendKeys(word);
    }

    public void clickAdvancedSearchBtn(){
        Waiter(advancedSearchBtn);
        driver.findElement(advancedSearchBtn).click();
    }







}
