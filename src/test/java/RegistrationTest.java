import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12.10.16.
 */
public class RegistrationTest {

    static WebDriver driver;
    HomePage objHomePage;
    RegistrationPage1 objRegistrationPage1;
    RegistrationPage2 objRegistrationPage2;


    @BeforeMethod
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\Documents and Settings\\Admin\\IdeaProjects\\TestProject\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();


    }

    @Test
    public void FullRegistration() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage2.CompleteRegistration2();

    }

    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }





}
