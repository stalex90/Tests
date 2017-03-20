import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 14.11.16.
 */
public class CabinetSellerProfile {
    int randomInt = 1000000000 + (int)(Math.random()*((2000000000-1000000000)+1));

    WebDriver driver;


    public CabinetSellerProfile(WebDriver driver) {
        this.driver = driver;
    }
    //Вход выход
    By LogoutBtn = By.xpath("//a[contains(@ui-sref,'logout')]");
    By loginFld = By.xpath(".//*[@id='username']");
    By password = By.xpath(".//*[@id='password']");
    By loginBtn = By.xpath(".//*[@id='_submit']");

    //Панель

    By panel = By.xpath(".//*[@id='side-menu']");
    By profilePnl = By.xpath(".//a[contains(@ui-sref,'profile.seller')]");

    //Профиль

    By description = By.xpath(".//*[@name='description']");
    By website = By.xpath(".//*[@name='website']");
    By save = By.xpath("//button[@ng-disabled='saving']");

    //Персональные данные

    By personalData = By.xpath("//a[contains(@ui-sref,'profile.personal')]");
    By secondName = By.xpath("//a[contains(@ui-sref,'profile.personal')]");
    By firstName = By.xpath(".//*[@name='first_name']");
    By middleName = By.xpath(".//*[@name='middle_name']");
    By gender = By.xpath(".//*[@name='sex']");
    By birthday = By.xpath(".//*[@name='birth_day']");
    By country = By.xpath(".//*[@name='country']");
    By index = By.xpath(".//*[@name='post_code']");
    By region = By.xpath(".//*[@name='region']");
    By city = By.xpath(".//*[@name='city']");
    By address = By.xpath(".//*[@name='address']");
    By passport = By.xpath(".//*[@name='num_passport']");
    By vidan = By.xpath(".//*[@name='org_passport']");
    By datePassport = By.xpath(".//*[@name='date_passport']");
    By countryPassport = By.xpath(".//*[@name='country_passport']");


    //Верификация

    By verification = By.xpath("//a[contains(@ui-sref,'verification')]");
    By titelVerif = By.xpath("//div[@class='ibox-title']");

    //Безопасность

    By security = By.xpath("//*[contains(@ui-sref,'security')]");
    By oldPassword = By.xpath("//input[@name='old_password']");
    By newPassword = By.xpath("//input[@name='new_password']");
    By repeatPassword = By.xpath("//input[@name='repeat_new_password']");


    //Персональные данные
    public void inputSecondName(){
        driver.findElement(secondName).clear();
        driver.findElement(secondName).sendKeys("Петросян");
    }

    public void inputFirstName(){
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys("Петр");
    }

    public void inputMiddleName(){
        driver.findElement(middleName).clear();
        driver.findElement(middleName).sendKeys("Петрович");
    }

    public void waiter(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(verification));
    }

    public void selectGender(String a) throws InterruptedException {
        driver.findElement(gender).click();
        Thread.sleep(2000);
        List<WebElement> CountryList = driver.findElements(By.xpath("//*[@name='sex']/option"));
        System.out.println(CountryList.size());
        CountryList.get(1).click();
    }









    //Логин
    public void inputLogin(String a){
        driver.findElement(loginFld).sendKeys(a);
    }

    public void inputPassword(String a){
        driver.findElement(password).sendKeys(a);
    }

    public void clickLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    public void login (String a, String b){
        inputLogin(a);
        inputPassword(b);
        clickLoginBtn();
    }

    public void clickLogoutBtn(){
        driver.findElement(LogoutBtn).click();
    }

    //Профиль

    public void clickProfilePnl(){
        driver.findElement(profilePnl).click();
    }

    public void inputDescription(){
        driver.findElement(description).clear();
        driver.findElement(description).sendKeys("test" + randomInt);
    }

    public String GetDescription(){
        return driver.findElement(description).getAttribute("value");
    }

    public void inputWebsite(){
        driver.findElement(website).clear();
        driver.findElement(website).sendKeys("https://devdashboard.pokupo.ru/" + randomInt);
    }

    public String GetWebsite(){
        return driver.findElement(website).getAttribute("value");
    }

    public void clickSave(){
        driver.findElement(save).click();
    }


    //Персональные данные

    public void clickPersonalData(){
        driver.findElement(personalData).click();
    }

    //Верификация

    public void clickVerification(){
        driver.findElement(verification).click();
    }

    //Безопасность

    public void clickSecurity(){
        driver.findElement(security).click();
    }








}
