import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 19.12.16.
 */
public class Messages {

    WebDriver driver;

    public Messages(WebDriver driver) {
        this.driver = driver;
    }

    By NewMsg = By.xpath("//button[contains(@class,'btn_add_message')]");
    By ClosIcn = By.xpath("//div[@id='modal-message']/div/div/div/span[contains(@class,'close-modal')]");
    By CancelBtn = By.xpath("//button[text()='Отмена']");
    By SendBtn = By.xpath("//button[text()='Отправить']");
    By Adresat = By.id("topic_user");
    By Thema = By.id("topic_name");
    By Text = By.id("topic_text");
    By CopyCheckbox = By.xpath("//label[@for='carboncopy']");
    By MessagePhoto = By.xpath(".//a[contains(@class,'info__photo')]");
    By ReMessagePhoto = By.xpath(" .//div[contains(@class,'info__photo')]");

    By AllCheckbox = By.xpath(".//label[contains(@data-bind,'SelectAll')]");
    By Checkboxes = By.xpath(".//label[contains(@data-bind,'Message')]");
    By Deletes = By.xpath(".//span[contains(@data-bind,'Delete')]");
    By MarkRead = By.xpath("//span[contains(@data-bind,'Read')]");
    By Back = By.xpath(".//a[contains(@data-bind,'Back')]");
    By Yes = By.xpath("//button[text()='Да']");
    By No = By.xpath("//button[text()='Нет']");
    By NoMsgs = By.xpath(".//p[text()='Сообщений не найдено']");
    By ReText = By.xpath(".//*[@id='simple_form_message']/textarea");

    By AdresatError = By.xpath("//p[contains(@data-bind,'dstUserError')]");
    By ThemaError = By.xpath("//p[contains(@data-bind,'topicNameError')]");
    By TextError = By.xpath("//p[contains(@data-bind,'textError')]");

    String EmptyMsg = "Поле обязательно для заполнения";
    String IncorrectAdresatMsg = "Получатель не найден.";



    public String GetAdresatError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AdresatError));
        return driver.findElement(AdresatError).getText();
    }

    public String GetThemaError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ThemaError));
        return driver.findElement(ThemaError).getText();
    }

    public String GetTextError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(TextError));
        return driver.findElement(TextError).getText();
    }


    public void ClickNewMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NewMsg));
        driver.findElement(NewMsg).click();
    }

    public void ClickSend(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SendBtn));
        driver.findElement(SendBtn).click();
    }

    public void ClickCopyCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CopyCheckbox));
        driver.findElement(CopyCheckbox).click();
    }

    public void ClickYes(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Yes));
        driver.findElement(Yes).click();
    }

    public void ClickNo(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(No));
        driver.findElement(No).click();
    }

    public void InputAdresat(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Adresat));
        driver.findElement(Adresat).sendKeys("login1983200342");
    }

    public void InputAdresat2(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Adresat));
        driver.findElement(Adresat).sendKeys("dsafadsfsd");
    }

    public void InputIncorrectAdresat(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Adresat));
        driver.findElement(Adresat).sendKeys("!!!!");
    }

    public void InputThema(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Thema));
        driver.findElement(Thema).sendKeys("Привет как дела");
    }

    public void InputText(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Text));
        driver.findElement(Text).sendKeys("Привет привет Привет привет Привет привет Привет привет Привет привет Привет привет Привет привет Привет привет");
    }

    public void InputReText(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ReText));
        driver.findElement(ReText).sendKeys("Как дела?????????? а???????");
    }

    public void ClickDeleteSelected(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Deletes));
        List<WebElement> AllDeletes = driver.findElements(Deletes);
        AllDeletes.get(AllDeletes.size()-1).click();
    }

    public void ClickDelete(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Deletes));
        List<WebElement> AllDeletes = driver.findElements(Deletes);
        AllDeletes.get(0).click();
    }

    public void ClickMessagePhoto(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MessagePhoto));
        List<WebElement> AllPhotos = driver.findElements(MessagePhoto);
        AllPhotos.get(0).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(MessagePhoto));
    }

    public void ClickSelectAll(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllCheckbox));
        driver.findElement(AllCheckbox).click();
    }

    public void DeleteAllMessages(){
        if (driver.findElements(Deletes).size()>0) {
            ClickSelectAll();
            ClickDeleteSelected();
            ClickYes();
            (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(Yes));
        }
    }

    public void Send1Messge(){
        ClickNewMsg();
        InputAdresat();
        InputThema();
        InputText();
        ClickSend();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SendBtn));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MessagePhoto));
    }

    public void Send1Messge2(){
        ClickNewMsg();
        InputAdresat2();
        InputThema();
        InputText();
        ClickSend();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SendBtn));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MessagePhoto));
    }









}
