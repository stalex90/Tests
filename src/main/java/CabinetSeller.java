import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 14.11.16.
 */
public class CabinetSeller {

    WebDriver driver;


    public CabinetSeller(WebDriver driver) {
        this.driver = driver;
    }

    By LogoutBtn = By.xpath("//a[contains(@ui-sref,'logout')]");
}
