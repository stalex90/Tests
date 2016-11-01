import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 01.11.16.
 */
public class Oformit {
    WebDriver driver;


    public Oformit(WebDriver driver) {
        this.driver = driver;
    }

    By OformitTitel = By.xpath(".//*[@id='content_pkp']/h1");
}

