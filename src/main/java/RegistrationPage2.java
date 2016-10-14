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

    CheckEmail objCheckEmail;
    By EmailField = By.xpath(".//input[text()='Ваш e-mail код']");
    By EmailCheckbox = By.xpath(".//label[text() = 'Подтвердить почтовый ящик позднее']");
    By PhoneCheckbox = By.xpath(".//label[text()='Подтвердить телефон позднее']");
    By ContinueBtn = By.xpath(".//a[text()='Продолжить']");

    public void ClickEmailCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailCheckbox));
        driver.findElement(EmailCheckbox).click();
    }

    public void ClickPhoneCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PhoneCheckbox));
        driver.findElement(PhoneCheckbox).click();
    }

    public void ClickContinueBtn(){
        driver.findElement(ContinueBtn).click();
    }

    public void InputEmailCode(){

        objCheckEmail = new CheckEmail();
        String s = objCheckEmail.CheckEmail();
        driver.findElement(EmailField).sendKeys(s);
    }

    public void CompleteRegistration2(){

        ClickPhoneCheckbox();
       // InputEmailCode();
        ClickContinueBtn();
    }



}
