import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12.10.16.
 */
public class CheckEmail {
    WebDriver driver;
    RegistrationPage1 objRegistrationPage1;
    String Email = "123123231";

    public String CheckEmail() {

        System.setProperty("webdriver.chrome.driver","C:\\Documents and Settings\\Admin\\IdeaProjects\\TestProject\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.mailinator.com/");

        driver.findElement(By.xpath(".//*[@id='recaptcha-anchor']/div[5]")).click();
        driver.findElement(By.id("inboxfield")).sendKeys(Email);
        driver.findElement(By.id("inboxfield")).sendKeys(Keys.ENTER);
        String k = "a";
        return k;

    }



}
