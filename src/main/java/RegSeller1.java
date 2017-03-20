import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 11.11.16.
 */
public class RegSeller1 {

    int a = 1000000000 + (int)(Math.random()*((2000000000-1000000000)+1)); // рандомное 10 значное число

    String EmailKey = "pokupotestreg+" + a + "@gmail.com" ;
    String NameKey = "name " + a;
    String DomainKey = "domain" + a;
    String SiteKey = a + ".test";
    String PhoneKey = "7" + a;

    String EmptyEmailMsg = "Это поле обязательно для заполнения";
    String IncorrectEmailMsg = "Введите корректный emailORphone";
    String ShortNameMsg = "Hазвание магазина не может быть менее 4 символов";
    String IncorrectNameMsg = "Логин/название магазина содержит недопустимые символы. Название магазина может содержать русские и латинские символы, цифры, подчеркивания, дефисы, точки и пробелы. Первым символом должна быть буква";
    String IncorrectDomainMsg ="Субдомен может содержать только латинские символы, цифры, дефис и подчеркивание. Первым и последним символами должны быть латинские символы.";
    String IncorrectSiteMsg = "Введите корректный URL";
    String IncorrectPhoneMsg = "Ошибочный формат номера телефона";


    WebDriver driver;
    public RegSeller1(WebDriver driver) {
        this.driver = driver;
    }

    By Chastnoe = By.xpath(".//span[contains(@class,'person')]"); //Частное лицо
    By Company = By.xpath(".//span[contains(@class,'company')]");   // Компания
    By Email = By.xpath(".//input[@name='emailORphone']"); //Поле емаил
    By Checkbox = By.xpath(".//*[@id='agreement']/div/label"); // Чекбокс согласия
    By Additional = By.xpath(".//*[@id='registerShopWizard']/div[3]/span"); // Линк дополнительная информация
    By Submit = By.xpath(".//button[contains(@class,'GetCode')]"); // Кнопка получить код на emailORphone

    By NameShop = By.xpath(".//input[@name='sellername']"); // Поле название магазина
    By Domain = By.xpath(".//input[@name='subdomain']"); // Поле желаемы поддомен
    By Site = By.xpath(".//input[@name='site']"); // Поле адрес сайта
    By Phone = By.xpath(".//input[@name='phone']"); // Поле номер телефона
    By Invite = By.xpath(".//input[@name='invite']"); // Поле код приглашения

    By EmailError = By.xpath(".//div[contains(@class,'emailError')]"); //Сообщение об ошибке emailORphone
    By NameError = By.xpath(".//div[contains(@class,'sellernameError')]");//Сообщение об ошибке название магазина
    By DomainError = By.xpath(".//div[contains(@class,'subdomainError')]");//Сообщение об ошибке домена
    By SiteError = By.xpath(".//div[contains(@class,'siteError')]");//Сообщение об ошибке адрес сайта
    By PhoneError = By.xpath(".//div[@class='errorField']"); //Сообщение об ошибке телефона

    //Getters--------------------------------------------------------------------------------

    public String GetEmailError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(EmailError));
        return driver.findElement(EmailError).getText();
    }

    public String GetNameError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NameError));
        return driver.findElement(NameError).getText();
    }

    public String GetDomainError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(DomainError));
        return driver.findElement(DomainError).getText();
    }

    public String GetSiteError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SiteError));
        return driver.findElement(SiteError).getText();
    }

    public String GetPhoneError(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(PhoneError));
        return driver.findElement(PhoneError).getText();
    }



    // Metshods-------------------------------------------------------------------------------



    public void ClickChastnoe(){ // Выбрать частное лицо
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Chastnoe));
        driver.findElement(Chastnoe).click();
    }

    public void ClickCompany(){ //Выбрать команию
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Company));
        driver.findElement(Company).click();
    }

    public void InputEmail(){ //Ввести емаил
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Email));
        driver.findElement(Email).sendKeys(EmailKey);
        driver.findElement(Email).sendKeys(Keys.ENTER);
    }

    public void InputIncorrectEmail(){ //Ввести некорректный емаил
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Email));
        driver.findElement(Email).sendKeys("asd");
        driver.findElement(Email).sendKeys(Keys.ENTER);
    }

    public void ClickCheckbox(){ //Нажать на чекбокс согласия
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(Checkbox),1,1).click().release().build().perform();

    }

    public void ClickSubmit(){ //Нажать получить код активации
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000);");
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Submit));
        driver.findElement(Submit).click();

    }

    public void ClickAdditional(){ //Нажать на дополнительные данные
        //(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Additional));
        driver.findElement(Additional).click();
    }

    public void InputName(){ //Ввести название магазина
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NameShop));
        driver.findElement(NameShop).sendKeys(NameKey);

    }

    public void InputShortName(){ //Ввести короткое название магазина
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NameShop));
        driver.findElement(NameShop).sendKeys("asd");
        driver.findElement(NameShop).sendKeys(Keys.ENTER);

    }

    public void InputIncorrectName(){ //Ввести некорректноне название магазина
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(NameShop));
        driver.findElement(NameShop).sendKeys("111111111");
        driver.findElement(NameShop).sendKeys(Keys.ENTER);

    }

    public void InputDomain(){ //Ввести желаемый поддомен
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Domain));
        driver.findElement(Domain).sendKeys(DomainKey);
    }

    public void InputIncorrectDomain(){ //Ввести некорректный желаемый поддомен
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Domain));
        driver.findElement(Domain).sendKeys("абв");
        driver.findElement(Domain).sendKeys(Keys.ENTER);
    }

    public void InputSite(){ //Ввести адрес сайта
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Site));
        driver.findElement(Site).sendKeys(SiteKey);
    }

    public void InputIncorrectSite(){ //Ввести некорректный адрес сайта
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Site));
        driver.findElement(Site).sendKeys("abc");
        driver.findElement(Site).sendKeys(Keys.ENTER);
    }

    public void InputPhone(){ //Ввести телефон
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Phone));
        driver.findElement(Phone).sendKeys(PhoneKey);
        driver.findElement(Phone).sendKeys(Keys.ENTER);
    }

    public void InputIncorrectPhone(){ //Ввести некорректный телефон
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Phone));
        driver.findElement(Phone).sendKeys("71234");
        driver.findElement(Phone).sendKeys(Keys.ENTER);
    }

    public void ChastnoeCompleteRegSeller1(){ //Полная регистрация частного лица
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Submit));
        ClickChastnoe();
        InputEmail();
        ClickAdditional();
        InputName();
        InputDomain();
        InputSite();
        InputPhone();
        ClickCheckbox();
        ClickSubmit();

    }

    public void CompanyCompleteRegSeller1(){ //Полная регистрация компании
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Submit));
        ClickCompany();
        InputEmail();
        ClickAdditional();
        InputName();
        InputDomain();
        InputSite();
        InputPhone();
        ClickCheckbox();
        ClickSubmit();
    }

    public void ChastnoeCompleteRegSellerWithoutPhone1(){ //Полная регистрация частного лица без телефона
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Submit));
        ClickChastnoe();
        InputEmail();
        ClickAdditional();
        InputName();
        InputDomain();
        InputSite();
        ClickCheckbox();
        ClickSubmit();

    }

    public void CompanyCompleteRegSellerWithoutPhone1(){ //Полная регистрация компании без телефона
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Submit));
        ClickCompany();
        InputEmail();
        ClickAdditional();
        InputName();
        InputDomain();
        InputSite();
        ClickCheckbox();
        ClickSubmit();
    }







}
