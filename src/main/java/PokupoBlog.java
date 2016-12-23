import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by user on 23.12.16.
 */
public class PokupoBlog {
    WebDriver driver;
    public PokupoBlog(WebDriver driver) {
        this.driver = driver;}

    By headerBlock = By.xpath("//*[@class='header']");
    By eventsBlock = By.xpath("//*[@class='events']");
    By footerBlock = By.xpath("//*[@class='footer']");
    By events = By.xpath("//*[@class='event blog_content']");

    public int getEvents() {
        List<WebElement> AllEvents = driver.findElements(events);
        return AllEvents.size();
    }
}
