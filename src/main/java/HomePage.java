import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 11.10.16.
 */
public class HomePage {
    WebDriver driver;
    String URL;



//    String URL = driver.getCurrentUrl();
    By LogoImage = By.xpath("//*[@id='shopInfoWidgetId']/a/div[1]");
    By LogoText = By.xpath("//*[@id='shopInfoWidgetId']/a/div[1]");
    By LoginBtn = By.id("js-login");
    By RegBtn = By.xpath(".//*[@class='not-logged-in']/a[text()='Регистрация']");
    By ProfileIcon = By.xpath(".//a[contains(@class,'icon__profile')]");
    By ProfileBtn = By.xpath(".//a[text()='Профиль']");
    By OrderBtn = By.xpath(".//a[text()='Заказы']");
    By MessageBtn = By.xpath(".//a[text()='Сообщения']");
    By LogoutBtn = By.xpath(".//a[text()='Выйти']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }

    public void LogoImage_RetunToHomepage() {

        //(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(LogoImage));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LogoImage));
        driver.findElement(LogoImage).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(LogoImage));

    }

    public void LogoText_RetunToHomepage() {
        //(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(LogoText));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LogoText));
        driver.findElement(LogoText).click();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(LogoText));
    }

    public  LoginPage ClickLoginBtn() {

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LoginBtn));
        driver.findElement(LoginBtn).click();
        return new LoginPage(driver);
    }

    public RegistrationPage1 ClickRegBtn() {

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LogoText));
        driver.findElement(RegBtn).click();
        return new RegistrationPage1(driver);
    }

    public void ClickProfileIcon() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ProfileIcon));
        driver.findElement(ProfileIcon).click();

    }

    public void ClickProfileBtn() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ProfileBtn));
        driver.findElement(ProfileBtn).click();

    }

    public void ClickOrderBtn() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(OrderBtn));
        driver.findElement(OrderBtn).click();

    }

    public void ClickMessageBtn() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MessageBtn));
        driver.findElement(MessageBtn).click();

    }

    public LogoutPopup ClickLogoutBtn() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LogoutBtn));
        driver.findElement(LogoutBtn).click();
        return new LogoutPopup(driver);

    }

    public By getProfileIcon() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ProfileIcon));
        return ProfileIcon;
    }






}
