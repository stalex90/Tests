import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 01.11.16.
 */
public class Cart {
    WebDriver driver;


    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    By CartTitel = By.xpath(".//*[@id='content_pkp']/div/h1");
}
