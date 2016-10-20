import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;


/**
 * Created by user on 11.10.16.
 */
public class LoginPage {

    WebDriver driver;

    String LoginN = "testpokupo@mail.ru";
    String PasswordN = "1234qwer";
    String InvalidLoginN = "invalid";
    String InvalidPasswordN = "1234asdf";

    By Header = By.xpath(".//h1[text()='Войти']");
    By Login = By.xpath(".//*[@name='username']");
    By Password = By.xpath(".//*[@name='password']");
    By LoginBtn = By.xpath(".//button[text()='Войти']");
    By ForgotBtn = By.xpath(".//a[text()='Восстановление пароля']");
    By RegBtn = By.xpath(".//li[*]/a[text()='Регистрация']");
    By CloseBtn = By.xpath(".//span[contains(@class,'close-popup')]");
    By WarningMsg = By.xpath(".//p[text()='Ошибка в логине или пароле']");

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

    public void InputInvalidLogin(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Login));
        driver.findElement(Login).sendKeys(InvalidLoginN);

    }

    public void InputPassword(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Password));
        driver.findElement(Password).sendKeys(PasswordN);
    }
    public void InputInvalidPassword(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Password));
        driver.findElement(Password).sendKeys(InvalidPasswordN);
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

    public void InvalidLoginComplete(){
        InputInvalidLogin();
        InputPassword();
        ClickLoginBtn();
    }

    public void InvalidPasswordComplete(){
        InputLogin();
        InputInvalidPassword();
        ClickLoginBtn();
    }

    public By getWarningMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(WarningMsg));
        return WarningMsg;
    }

    public void ClickCloseBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CloseBtn));
        driver.findElement(CloseBtn).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(CloseBtn));
    }

    public void SwitchNewTab() throws InterruptedException {
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void ClickForgotBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ForgotBtn));
        driver.findElement(ForgotBtn).click();

    }

    public RegistrationPage1 ClickRegBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegBtn));
        driver.findElement(RegBtn).click();
        return new RegistrationPage1(driver);

    }





}
