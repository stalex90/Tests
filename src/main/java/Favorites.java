import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 28.11.16.
 */
public class Favorites {

    WebDriver driver;

    public Favorites(WebDriver driver) {
        this.driver = driver;
    }

    By Favorites = By.xpath(".//a[@class='icon__favorites']");
    By Support = By.xpath(".//span[@data-target='support']");
    By CheckboxAll = By.xpath(".//label[contains(@data-bind,'SelectAll')]");
    By Delete = By.xpath(".//span[contains(@data-bind,'Remove')]");
    By CartBtn = By.xpath(".//button[contains(@class,'btn-sm')][1]");
    By BuyNow = By.xpath(".//button[contains(@class,'btn-sm')][2]");
    By DeleteFromFavor = By.xpath(".//span[text()='Удалить из избранного']");
    By ClearAll = By.xpath(".//span[text()='Очистить избранное от товаров']");
    
}
