import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 21.12.16.
 */
public class Waiters {
    WebDriver driver;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresentWaiters(By locator){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        List<WebElement> list = driver.findElements(locator);

        if (list.size()==0){
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

}
