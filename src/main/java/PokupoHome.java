import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by user on 23.12.16.
 */
public class PokupoHome {
    WebDriver driver;
    public PokupoHome(WebDriver driver) {
        this.driver = driver;}

    By logo = By.xpath("//a[@class='logo']");
    By headerBlock = By.xpath("//*[@class='header1']");
    By videoBlock = By.xpath("//*[@class='video']");
    By createBlock = By.xpath("//*[@class='register orange patterned']");
    By casesBlock = By.xpath("//*[@class='cases']");
    By benefitsBlock = By.xpath("//*[@class='benefits']");
    By portalBlock = By.xpath("//*[@class='portal']");
    By missionBlock = By.xpath("//*[@class='mission']");
    By seoBlock = By.xpath("//*[@class='grey seo-frontpage']");
    By footerBlock = By.xpath("//*[@class='footer']");
}
