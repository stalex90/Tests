import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 24.10.16.
 */
public class RegistrationPage3 {

    WebDriver driver;

    public RegistrationPage3(WebDriver driver) {
        this.driver = driver;
    }

    String Sname = "TestSecond";
    String Fname = "TestFirst";
    String Mname = "TestMiddle";
    String Bday = "11111990";

    By SecondName = By.name("sname");
    By FirstName = By.name("fname");
    By MiddleName = By.name("mname");
    By Birthday = By.id("birthDay");
    By WomanRadioBtn = By.xpath(".//label[text()='Женский']");
    By ManRadioBtn = By.xpath(".//label[text()='Мужской']");
    By Checkbox = By.xpath(".//label[text()='Указать позже']");
    By ContinueBtn = By.xpath(".//a[text()='Продолжить']");
    By SecondNameWarning = By.xpath(".//p[contains(@data-bind,'errorLastName')]");
    By FirstNameWarning = By.xpath(".//p[contains(@data-bind,'errorFirstName')]");
    By BirthDayWarning = By.xpath(".//p[contains(@data-bind,'errorBirthDay')]");

    //Getters-------------------------------------------------------
    public String GetSecondNameWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SecondNameWarning));
        return driver.findElement(SecondNameWarning).getText();
    }

    public String GetFirstNameWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstNameWarning));
        return driver.findElement(FirstNameWarning).getText();
    }

    public String GetBirthDayWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BirthDayWarning));
        return driver.findElement(BirthDayWarning).getText();
    }





    //Methods-------------------------------------------------------
    public void InputSecondName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SecondName));
        driver.findElement(SecondName).sendKeys(Sname);
    }

    public void InputFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).sendKeys(Fname);
    }

    public void InputMiddledName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MiddleName));
        driver.findElement(MiddleName).sendKeys(Mname);
    }

    public void InputBirthday(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Birthday));
        driver.findElement(Birthday).sendKeys(Bday);
    }

    public void ClickWomanBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(WomanRadioBtn));
        driver.findElement(WomanRadioBtn).click();
    }

    public RegistrationPage4 ClickCheckBox() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Checkbox));
        driver.findElement(Checkbox).click();
        return new RegistrationPage4(driver);
    }

    public void ClickContinueBtn() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ContinueBtn));
        driver.findElement(ContinueBtn).click();
    }

    public RegistrationPage4 CompleteRegistration3() {
        InputSecondName();
        InputFirstName();
        InputMiddledName();
        InputBirthday();
        ClickWomanBtn();
        ClickContinueBtn();
        return new RegistrationPage4(driver);
    }

    public void InputShortSecondName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SecondName));
        driver.findElement(SecondName).sendKeys("А");
    }

    public void InputShortFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).sendKeys("А");
    }

    public void InputInvalidSecondName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SecondName));
        driver.findElement(SecondName).sendKeys("!!!!");
    }

    public void InputInvalidFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).sendKeys("!!!!");
    }










}
