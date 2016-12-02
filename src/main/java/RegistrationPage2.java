import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 12.10.16.
 */
public class RegistrationPage2 {

    WebDriver driver;

    public RegistrationPage2(WebDriver driver) {
        this.driver = driver;
    }


    By EmailField = By.xpath(".//input[contains(@data-bind,'mailToken')]");
    By SMSField = By.xpath(".//input[contains(@data-bind,'phoneToken')]");
    By EmailCheckbox = By.xpath(".//label[text() = 'Подтвердить почтовый ящик позднее']");
    By PhoneCheckbox = By.xpath(".//label[text()='Подтвердить телефон позднее']");
    By ContinueBtn = By.xpath(".//a[text()='Продолжить']");
    By EmailWarning = By.xpath(".//p[contains(@data-bind,'errorEmail')]");
    By SMSWarning = By.xpath(".//p[contains(@data-bind,'errorPhone')]");
    By VerifyCodeError = By.xpath(".//p[contains(@data-bind,'message')]");

    //Getters------------------------------------------------------------------

    public String GetEmailWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailWarning));
        return driver.findElement(EmailWarning).getText();
    }

    public String GetSMSWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SMSWarning));
        return driver.findElement(SMSWarning).getText();
    }

    public String GetVerifyError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(VerifyCodeError));
        return driver.findElement(VerifyCodeError).getText();
    }


    // Methods-----------------------------------------------------------------
    public void ClickEmailCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailCheckbox));
        driver.findElement(EmailCheckbox).click();
    }

    public void ClickPhoneCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PhoneCheckbox));
        driver.findElement(PhoneCheckbox).click();
    }

    public void ClickContinueBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ContinueBtn));
        driver.findElement(ContinueBtn).click();
    }

    public void InputEmailCode() throws InterruptedException {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        Thread.sleep(40000);
        String Code = new Gmail().GetEmailCode();
        driver.findElement(EmailField).sendKeys(Code);
    }

    public void InputInvalidEmailCode(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailField));
        driver.findElement(EmailField).sendKeys("test");

    }

    public void InputInvalidSMSCode(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SMSField));
        driver.findElement(SMSField).sendKeys("test");

    }

    public RegistrationPage3 CompleteRegistration2() throws InterruptedException {

        InputEmailCode();
        ClickPhoneCheckbox();
        ClickContinueBtn();
        return new RegistrationPage3(driver);
    }



}
