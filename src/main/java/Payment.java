import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13.12.16.
 */
public class Payment {

    WebDriver driver;
    public Payment(WebDriver driver) {
        this.driver = driver;
    }

    By Summa = By.xpath(".//*[@id='pokupo_amount']");
    By Email = By.xpath(".//*[@id='pokupo_email']");
    By Zpay = By.xpath(".//*[@id='pokupo_payment_form']/div/div[1]");
    By Intellectpay = By.xpath(".//*[@id='pokupo_payment_form']/div/div[2]");
    By Payu = By.xpath(".//*[@id='pokupo_payment_form']/div/div[3]");
    By Payinout = By.xpath(".//*[@id='pokupo_payment_form']/div/div[4]");
    By Payphone = By.xpath(".//*[@id='pokupo_payment_form']/div/div[5]");
    By BackBtn = By.xpath(".//a[text()='Назад']");
    By NextBtn = By.xpath(".//button[text()='Далее']");
    By SupportLink = By.xpath("//a[text()='Помощь']");
    By CheckboxAgree = By.xpath(".//*[@id='AcceptAgreement']");
    By Agreement = By.cssSelector("ValueTable>div>a");
    By Pays = By.xpath("//input[contains(@class,'inputNext')]");
    By GoPay = By.xpath("//input[contains(@class,'inputNext')]");
    By NextBtn2 = By.xpath("//input[contains(@class,'inputNext')]");
    By ReturnToSite = By.xpath("//input[contains(@class,'inputReturn')]");
    By ReturnToShop = By.xpath("//input[@value='Вернуться в магазин']");
    By CancelPay = By.xpath("//input[@value='Отменить оплату']");
    By FromWallet = By.xpath("//input[@name='FROM_ACCOUNT']");
    By SuccessStatus = By.xpath(".//*[@id='pkp-container']/div[1]/table/tbody[5]/tr/td[2]");
    By WaitStatus = By.xpath(".//*[@id='pkp-container']/div[1]/table/tbody[4]/tr/td[2]");
    By StatusTitel = By.xpath(".//*[@id='pkp-container']/div[1]/table/tbody[5]/tr/td[1]");
    By PayPass = By.xpath(".//*[@id='PAY_PASS']");
    By YandexWallet = By.xpath("//input[@name='YANDEX_ACCOUNT']");
    By YandexNext = By.xpath("//input[@value='Далее']");
    By YandexGoPay = By.xpath("//input[@value='Перейти к оплате']");
    By YandexDomik = By.xpath("//div[@class='b-domik__form']");
    By Pereyti = By.xpath("//input[@value='Перейти']");
    By Card3DSecureCheck = By.cssSelector(".b-page-cps__title");
    By PrintChek = By.xpath("//input[@value='Распечатать квитанцию']");
    By CoinsNext = By.xpath("//input[contains(@class,'inputNext')]");
    By CoinsRefresh = By.xpath("//input[contains(@class,'inputReturn')]");


    By IntellectNext = By.xpath("//button[contains(@data-bind,'ClickPay')]");
    By IntellectCheck = By.xpath("//h3[text()='Пожалуйста, выберите любой из доступных способов оплаты Вашего счета:']");

    By PayUCheck = By.xpath(".//*[@id='tiCNumber']");

    By PayInOutPhone = By.xpath(".//*[@id='field_PKP_MOBILE_PHONE']");
    By PayInOutNext = By.xpath("//button[contains(@data-bind,'ClickSubmit')]");
    By PayinoutPAY = By.xpath("//button[contains(@data-bind,'ClickPay')]");
    By PayinoutCheck = By.xpath("//input[@value='Оплатить']");

    By ErrorSumma = By.xpath(".//span[contains(@data-bind,'error.amount')]");
    By ErrorEmail = By.xpath(".//span[contains(@data-bind,'error.email')]");
    By ErrorWallet = By.xpath("//span[@class='TextError']");



    String StatusMsg = "Успешно оплачено!";
    String EmptyErrorMsg = "Поле обязательно для заполнения";
    String IncorrectSummMsg = "Недопустимое значение.";
    String IncorrectEmailMsg = "Строка не является адресом электронной почты";
    String IncorrectWallerMsg = "Номер счета плательщика не найден!";
    String IncorrectSummforWalletMsg = "Сумма оплаты 1000 zp превышает баланс аккаунта ZP00887515. Пополните свой аккаунт и повторите перевод.";
    String IncorrectPayPassMsg = "Неверный платежный пароль! Проверьте язык и регистр ввода. Для восстановления пароля используйте форму сброса паролей.";




    public String GetSuccessStatus(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(StatusTitel));
        return driver.findElement(SuccessStatus).getText();
    }

    public String GetSumm(){
        return driver.findElement(Summa).getAttribute("value");
    }

    public String GetWalletError(){
        return driver.findElement(ErrorWallet).getText();
    }

    public String GetEmail(){
        return driver.findElement(Email).getAttribute("value");
    }

    public String GetWaitStatus(){
        return driver.findElement(WaitStatus).getText();
    }

    public String GetSummError(){
        return driver.findElement(ErrorSumma).getText();
    }

    public String GetEmailError(){
        return driver.findElement(ErrorEmail).getText();
    }

    public void InputSumm(String i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Summa));
        driver.findElement(Summa).sendKeys(i);

    }

    public void InputPayPass(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PayPass));
        driver.findElement(PayPass).sendKeys("df1de3");
    }

    public void InputYandexWallet(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(YandexWallet));
        driver.findElement(YandexWallet).sendKeys("410011727964341");
    }

    public void InputIncorrectPayPass(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PayPass));
        driver.findElement(PayPass).sendKeys("123456");

    }

    public void InputEmail(String i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Email));
        driver.findElement(Email).sendKeys(i);

    }

    public void InputWallet(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FromWallet));
        driver.findElement(FromWallet).sendKeys("ZP00887515");
    }

    public void InputPochta(){
        driver.findElement(By.xpath("//input[@name='S_NAME']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//input[@name='F_NAME']")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[@name='M_NAME']")).sendKeys("Иванович");
        driver.findElement(By.xpath("//input[@name='INDEX']")).sendKeys("460020");
        driver.findElement(By.xpath("//input[@name='OBLAST']")).sendKeys("Оренбургская");
        driver.findElement(By.xpath("//input[@name='SITY']")).sendKeys("Оренбург");
        driver.findElement(By.xpath("//input[@name='ADRESS']")).sendKeys("Москва Кремль");
    }

    public void InputPayinoutPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PayInOutPhone));
        driver.findElement(PayInOutPhone).sendKeys("79121111111");
    }

    public void ClickZpay(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Zpay));
        driver.findElement(Zpay).click();
    }

    public void ClickIntellectpay(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Intellectpay));
        driver.findElement(Intellectpay).click();
    }

    public void ClickPayinout(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Payinout));
        driver.findElement(Payinout).click();
    }

    public void ClickPayphone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Payphone));
        driver.findElement(Payphone).click();
    }

    public void ClickPayu(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Payu));
        driver.findElement(Payu).click();
    }

    public void ClickBackBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BackBtn));
        driver.findElement(BackBtn).click();
    }

    public void ClickNextBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NextBtn));
        driver.findElement(NextBtn).click();
    }

    public void ClickSupportLink(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SupportLink));
        driver.findElement(SupportLink).click();
    }

    public void ClickCheckboxAgree(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CheckboxAgree));
        driver.findElement(CheckboxAgree).click();
    }

    public void ClickAgreement(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Agreement));
        driver.findElement(Agreement).click();
    }

    public void ClickPays(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Pays));
        List<WebElement> AllPays = driver.findElements(Pays);
        AllPays.get(i).click();
    }

    public void ClickReturnToSite(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ReturnToSite));
        driver.findElement(ReturnToSite).click();
    }

    public void ClickReturnToShop(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ReturnToShop));
        driver.findElement(ReturnToShop).click();
    }

    public void ClickGoPay(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(GoPay));
        driver.findElement(GoPay).click();
    }

    public void ClickNextBtn2(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NextBtn2));
        driver.findElement(NextBtn2).click();
    }

    public void ClickCancelPay(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NextBtn2));
        driver.findElement(CancelPay).click();
    }

    public void UpdateStatus() throws InterruptedException {
        int k=30;
        while (GetWaitStatus().equals("В обработке") || GetSuccessStatus().equals("В обработке") && k > 0){
            driver.navigate().refresh();
            k--;
            Thread.sleep(2000);
        }
    }

    public void ClickYandexNext(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(YandexNext));
        driver.findElement(YandexNext).click();
    }

    public void ClickYandexGoPay(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(YandexGoPay));
        driver.findElement(YandexGoPay).click();
    }

    public void ClickPereyti(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Pereyti));
        driver.findElement(Pereyti).click();
    }

    public void ClickCoinNext(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CoinsNext));
        driver.findElement(CoinsNext).click();
    }

    public void ClickIntellectNext(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IntellectNext));
        driver.findElement(IntellectNext).click();
    }

    public void ClickPayinoutNext(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PayInOutNext));
        driver.findElement(PayInOutNext).click();
    }

    public void ClickPayinoutPAY(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PayinoutPAY));
        driver.findElement(PayinoutPAY).click();
    }




    public void SwitchTab() throws InterruptedException {
        while  ((new ArrayList<String>(driver.getWindowHandles())).size() != 2)
        {
            Thread.sleep(1000);
        }
        driver.close();
        Thread.sleep(3000);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
    }
















}
