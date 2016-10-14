import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 12.10.16.
 */
public class LogoutPopup {

    WebDriver driver;
    public LogoutPopup(WebDriver driver) {
        this.driver = driver;
    }

    By WidgetName = By.xpath(".//h3[text()='Вы действительно хотите выйти?']");

    public String GetWidgetName(){
        return driver.findElement(WidgetName).getText();
    }
}
