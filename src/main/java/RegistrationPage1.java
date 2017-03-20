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
    String EmailN = "pokupotest+" + a + "@gmail.com";
    String PhoneN = "7" + a;
    String PasswordN = "1234qwer";



    WebDriver driver;

    public RegistrationPage1(WebDriver driver) {
        this.driver = driver;
    }

    By RegistrationHeader = By.xpath(".//h1[text()='Регистрация пользователя']");
    By Login = By.xpath(".//input[@name ='nickname']");
    By Email = By.xpath(".//input[@name='emailORphone']");
    By Phone = By.id("phone");
    By FirstPassword = By.id("firstPassword");
    By SecondPassword = By.id("secondPassword");
    By Checkbox = By.xpath(".//span[text()='Я принимаю условия']");
    By ContinueBtn = By.xpath(".//a[text() = 'Продолжить']");
    By LoginWarning = By.xpath(".//p[contains(@data-bind,'errorUsername')]");
    By EmailWarning = By.xpath(".//p[contains(@data-bind,'errorEmail')]");
    By PhoneWarning = By.xpath(".//p[contains(@data-bind,'errorPhone')]");
    By FirstPswWarning = By.xpath(".//p[contains(@data-bind,'errorFirstPassword')]");
    By SecondPswWarning = By.xpath(".//p[contains(@data-bind,'errorSecondPassword')]");
    By CheckboxWarning = By.xpath(".//p[contains(@data-bind,'errorIsChecked')]");

    //Getters--------------------------------------------------------
    public String GetEmail(){

        return EmailN;
    }

    public String GetNameWidget(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegistrationHeader));
        return driver.findElement(RegistrationHeader).getText();
    }

    public String GetLoginWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LoginWarning));
        return driver.findElement(LoginWarning).getText();
    }

    public String GetEmailWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailWarning));
        return driver.findElement(EmailWarning).getText();
    }

    public String GetPhoneWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PhoneWarning));
        return driver.findElement(PhoneWarning).getText();
    }

    public String GetFirstPswWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstPswWarning));
        return driver.findElement(FirstPswWarning).getText();
    }

    public String GetSecondPswWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SecondPswWarning));
        return driver.findElement(SecondPswWarning).getText();
    }

    public String GetCheckboxWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CheckboxWarning));
        return driver.findElement(CheckboxWarning).getText();
    }





    //Methods--------------------------------------------------------

    public void InputLogin(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Login));
        driver.findElement(Login).sendKeys(LoginN);
    }

    public void InputShortLogin(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Login));
        driver.findElement(Login).sendKeys("Aa");
    }

    public void InputEmail(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Email));
        driver.findElement(Email).sendKeys(EmailN);
    }

    public void InputInvalidEmail(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Email));
        driver.findElement(Email).sendKeys("asd@basd");
    }

    public void InputPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Phone));
        driver.findElement(Phone).click();
        driver.findElement(Phone).sendKeys(PhoneN);
    }

    public void InputInvalidPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Phone));
        driver.findElement(Phone).click();
        driver.findElement(Phone).sendKeys("1234");
    }

    public void InputFirstPassword(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstPassword));
        driver.findElement(FirstPassword).sendKeys(PasswordN);
    }

    public void InputShortPassword(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstPassword));
        driver.findElement(FirstPassword).sendKeys("11111");
    }

    public void InputSecondPassword(){
        driver.findElement(SecondPassword).sendKeys(PasswordN);
    }

    public void ClickCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Checkbox));
        driver.findElement(Checkbox).click();
    }

    public void ClickContinueBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ContinueBtn));
        driver.findElement(ContinueBtn).click();
    }

    public RegistrationPage2 CompleteRegistration1() {
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
