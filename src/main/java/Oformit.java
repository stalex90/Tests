import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 01.11.16.
 */
public class Oformit {
    WebDriver driver;


    public Oformit(WebDriver driver) {
        this.driver = driver;
    }

    By OformitTitel = By.xpath(".//*[@id='content_pkp']/h1");
    By SelectAddressTitel = By.xpath(".//h2[text()='Выберите адрес доставки']");
    By addressCheckboxes = By.xpath("//label[@rel='tooltip']");
    By addAddress = By.xpath("//button[contains(@data-bind,'ClickAddAddress')]");
    By backBtn = By.xpath("//a[contains(@data-bind,'Back')]");
    By continueBtn = By.xpath("//a[contains(@data-bind,'Submit')]");
    By shipCheckboxes = By.xpath("//label[@rel='tooltip']");
    By payCheckboxes = By.xpath("//label[@rel='tooltip']");
    By cancelOrder = By.xpath("//a[contains(@data-bind,'ClickDelete')]");
    By confirmOrder = By.xpath("//a[contains(@data-bind,'ClickConfirm')]");
    By noBtn = By.xpath("//button[text()='Нет']");
    By yesBtn = By.xpath("//button[text()='Да']");
    By payBtn = By.xpath("//div[contains(@data-bind,'ClickPay')]");
    By payHeader = By.xpath(".//*[@id='content_pkp']/div/h1");
    By cancelRoundBtn = By.xpath("//div[contains(@data-bind,'ClickCancel')]");
    By refreshBtn = By.xpath("//div[contains(@data-bind,'ClickRefresh')]");
    By repeatBtn = By.xpath("//div[contains(@data-bind,'ClickRepeat')]");
    By incartBtn = By.xpath("//div[contains(@data-bind,'ClickReturn')]");
    By success = By.xpath("//h3[contains(@class,'icon-success')]");
    By error = By.xpath("//p[@data-bind='i18n: message']");

    By country = By.xpath(".//*[@id='country_list_chosen']/a/span");
    By region = By.xpath(".//*[@id='region_list']");
    By city = By.xpath(".//*[@id='city_list']");
    By address = By.xpath(".//*[@id='address']");
    By index = By.xpath(".//*[@id='post_index']");
    By fio = By.xpath(".//*[@id='delivery_addressee']");
    By phone = By.xpath(".//*[@id='delivery_contact_phone']");
    By firstsearchresult = By.xpath(".//*[@id='country_list_chosen']/div/ul/li");




    String succesMsg= "Ваш заказ подтвержден.";
    String succesAdrMsg = "Данные успешно сохранены.";
    String errorAdr = "Необходимо выбрать адрес доставки.";
    String errorSposob = "Необходимо выбрать метод доставки.";
    String errorPay = "Необходимо выбрать способ оплаты.";
    String cancelMsg = "Ваш заказ удален.";

    public void ClickCountryField(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(country));
        driver.findElement(country).click();
    }

    public void InputCountry() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(firstsearchresult));
        driver.findElement(firstsearchresult).click();
    }

    public void InputRegion(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(region));
        driver.findElement(region).sendKeys("Московская область");
    }

    public void InputCity(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(city));
        driver.findElement(city).sendKeys("Москва");
    }

    public void InputAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(address));
        driver.findElement(address).sendKeys("Кутузовская 10");
        driver.findElement(address).sendKeys(Keys.ENTER);
    }

    public void InputIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(index));
        driver.findElement(index).clear();
        driver.findElement(index).sendKeys("55555");
    }

    public void InputFIO(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(fio));
        driver.findElement(fio).sendKeys("Иван Иванцов");
    }

    public void InputShipPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(phone));
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys("71234567899");
    }

    public void inputAllAddress(){
        ClickCountryField();
        InputCountry();
        InputRegion();
        InputCity();
        InputAddress();
        InputIndex();
        InputFIO();
        InputShipPhone();
        clickContinueBtnInvis();
    }


    public String GetSuccessMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(success));
        return driver.findElement(success).getText();
    }

    public String GetErrorMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(error));
        return driver.findElement(error).getText();
    }

    public void clickaddressCheckboxes(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(addressCheckboxes));
        List<WebElement> All = driver.findElements(addressCheckboxes);
        All.get(i).click();
    }

    public void clickAddAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(addAddress));
        driver.findElement(addAddress).click();
    }

    public void clickBackBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(backBtn));
        driver.findElement(backBtn).click();
    }

    public void clickContinueBtnInvis(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        driver.findElement(continueBtn).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(continueBtn));
    }

    public void clickContinueBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
        driver.findElement(continueBtn).click();
    }

    public void clickShipCheckboxes(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(shipCheckboxes));
        List<WebElement> All = driver.findElements(shipCheckboxes);
        All.get(i).click();
    }

    public void clickPayCheckboxes(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(payCheckboxes));
        List<WebElement> All = driver.findElements(payCheckboxes);
        All.get(i).click();
    }

    public void clickCancelOrder(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(cancelOrder));
        driver.findElement(cancelOrder).click();
    }

    public void clickConfirmOrder(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(confirmOrder));
        driver.findElement(confirmOrder).click();
    }

    public void clickYes(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(yesBtn));
        driver.findElement(yesBtn).click();
    }

    public void clickNo(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(noBtn));
        driver.findElement(noBtn).click();
    }

    public void clickPayBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(payBtn));
        driver.findElement(payBtn).click();
    }

    public void clickCancelRoundBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(cancelRoundBtn));
        driver.findElement(cancelRoundBtn).click();
    }

    public void clickRefreshBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(refreshBtn));
        driver.findElement(refreshBtn).click();
    }

    public void clickRepeatBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(repeatBtn));
        driver.findElement(repeatBtn).click();
    }

    public void clickincartBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(incartBtn));
        driver.findElement(incartBtn).click();
    }





}

