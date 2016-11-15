import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 14.11.16.
 */
public class RegSeller2 {

    WebDriver driver;

    public RegSeller2(WebDriver driver) {
        this.driver = driver;
    }

    String IncorrectCode = "Не удалось выполнить активацию, проверьте правильность указанных кодов";
    String EmptyCode = "Введите код подтверждения";
    String IncorrectSMSCode = "SMS код указан неверно";

    By EmailCode = By.id("mail_token"); //емаил код
    By SmsCode = By.id("sms_token"); // смс код
    By BackBtn = By.xpath(".//span[contains(@class,'backButton')]"); // кнопка назад
    By SubmitBtn = By.xpath(".//button[text()='Закончить регистрацию']");// кнопка закончить регистрацию
    By CodeError = By.xpath(".//div[contains(@class,'errorFieldCode')]"); // сообщения об ошибке кодов


    //Getters--------------------------------------------------------------------
    public String GetCodeError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CodeError));
        return driver.findElement(CodeError).getText();
    }

    //Methods--------------------------------------------------------------------

    public void InputEmailCode() throws InterruptedException { //Ввести емаил код
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailCode));
        Thread.sleep(40000);
        String Code = new Gmail().GetEmailCode();
        driver.findElement(EmailCode).sendKeys(Code);
        System.out.println("Code");
    }

    public void InputSMSCode()  { //Ввести смс код
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SmsCode));
        driver.findElement(SmsCode).sendKeys("46476426342423");
    }

    public void InputIncorrectSMSCode()  { //Ввести некорректный смс код
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SmsCode));
        driver.findElement(SmsCode).sendKeys("test");
    }

    public void InputIncorrectEmailCode()  { //Ввести некорректнрый смс код
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailCode));
        driver.findElement(EmailCode).sendKeys("test");
    }

    public void ClickBack(){ //Нажать кнопку назад
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BackBtn));
        driver.findElement(BackBtn).click();
    }

    public void ClickSubmit(){ //Нажать кнопку закончить регистрацию
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SubmitBtn));
        driver.findElement(SubmitBtn).click();
    }

    public void EmailCompleteRegSeller2() throws InterruptedException { //Корректное заполнение и окончание регистрации
        InputEmailCode();
        InputSMSCode();
        ClickSubmit();
    }

}