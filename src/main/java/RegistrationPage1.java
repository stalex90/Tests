import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 11.10.16.
 */
public class RegistrationPage1 {
    int a1 = 1000000000 + (int)(Math.random()*((2000000000-1000000000)+1));

    String a = Integer.toString(a1);
    String LoginN = "login" + a;
    String EmailN = a + "@mailinator.com";
    String PhoneN = "7" + a;
    String PasswordN = "1234qwer";



    WebDriver driver;

    public RegistrationPage1(WebDriver driver) {
        this.driver = driver;
    }

    By RegistrationHeader = By.xpath(".//h1[text()='Регистрация пользователя']");
    By Login = By.xpath(".//input[@name ='nickname']");
    By Email = By.xpath(".//input[@name='email']");
    By Phone = By.id("phone");
    By FirstPassword = By.id("firstPassword");
    By SecondPassword = By.id("secondPassword");
    By Checkbox = By.xpath(".//span[text()='Я принимаю условия']");
    By ContinueBtn = By.xpath(".//a[text() = 'Продолжить']");

    public String GetEmail(){

        return EmailN;
    }

    public String GetNameWidget(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegistrationHeader));
        return driver.findElement(RegistrationHeader).getText();
    }

    public void InputLogin(){
        driver.findElement(Login).sendKeys(LoginN);
    }

    public void InputEmail(){
        driver.findElement(Email).sendKeys(EmailN);
    }


    public void InputPhone(){
        driver.findElement(Phone).click();
        driver.findElement(Phone).sendKeys(PhoneN);
    }

    public void InputFirstPassword(){
        driver.findElement(FirstPassword).sendKeys(PasswordN);
    }

    public void InputSecondPassword(){
        driver.findElement(SecondPassword).sendKeys(PasswordN);
    }

    public void ClickCheckbox(){
        driver.findElement(Checkbox).click();
    }

    public void ClickContinueBtn(){
        driver.findElement(ContinueBtn).click();
    }

    public RegistrationPage2 CompleteRegistration1() {
        System.out.println(a1);
        InputLogin();
        InputEmail();
        InputPhone();
        InputFirstPassword();
        InputSecondPassword();
        ClickCheckbox();
        ClickContinueBtn();

        return new RegistrationPage2(driver);

    }




}
