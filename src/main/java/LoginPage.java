import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by user on 11.10.16.
 */
public class LoginPage {

    WebDriver driver;

    String LoginN = "testpokupo@mail.ru";
    String PasswordN = "1234qwer";

    By Header = By.xpath(".//h1[text()='Войти']");
    By Login = By.xpath(".//*[@name='username']");
    By Password = By.xpath(".//*[@name='password']");
    By LoginBtn = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String GetNameWidget(){
       (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Header));

       return driver.findElement(Header).getText();
    }

    public void InputLogin(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Login));
        driver.findElement(Login).sendKeys(LoginN);

    }

    public void InputPassword(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Password));
        driver.findElement(Password).sendKeys(PasswordN);
    }

    public void ClickLoginBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LoginBtn));
        driver.findElement(LoginBtn).click();
    }

    public void CompleteLogin(){
        InputLogin();
        InputPassword();
        ClickLoginBtn();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(LoginBtn));

    }



}
