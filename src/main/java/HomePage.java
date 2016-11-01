import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by user on 11.10.16.
 */
public class HomePage {
    WebDriver driver;
    String URL;
    int i;



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
    By CartIcon = By.id("cart_dropdown__trigger");
    By CartCount = By.xpath(".//*[@id='cart_dropdown__trigger']/sup");
    By AllCartItems = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li");
    By ItemName = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[" + i+ "]/div[1]/span");
    By ItemCount = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[2]/span/span[2]");
    By ItemCountAdd = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[3]");
    By ItemCountRemove = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[1]");
    By ItemPrice = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[3]/span[1]");
    By ItemDelete = By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[4]");
    By FinalCost = By.xpath(".//span[contains(@data-bind,'finalCost')]");
    By InCart = By.xpath(".//a[text()='В корзину']");
    By Oformit = By.xpath(".//a[text()='Оформить']");
    By CartName = By.xpath(".//*[@id='cart_dropdown__content']/p");


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

    public void ClickCartIcon(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CartIcon));
        driver.findElement(CartIcon).click();
    }

    //Количество на иконке корзины
    public int GetCartCount(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CartCount));
        return Integer.parseInt(driver.findElement(CartCount).getText());
    }

    public void DeleteItem(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[4]")));
        driver.findElement(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[4]")).click();
    }

    public String GetItemName(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ItemName));
        this.i = i;
        return driver.findElement(ItemName).getText();
    }

    //Количество(единиц) определенного товара
    public int GetItemCount(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[2]/span/span[2]")));
        return Integer.parseInt(driver.findElement(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[2]/span/span[2]")).getText());
    }

    public void ItemCountAdd(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[3]")));
        driver.findElement(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[3]")).click();
    }

    public void ItemCountRemove(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[1]")));
        driver.findElement(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li[1]/div[2]/span/span[1]")).click();
    }

    public int GetItemPrice(int i){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[3]/span[1]")));
        return Integer.parseInt(driver.findElement(By.xpath(".//*[@id='cart_dropdown__content']/div/ol/li["+ i +"]/div[3]/span[1]")).getText());
    }

    //Количество продуктов добавленных в корзину
    public int GetItemsCount(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AllCartItems));
        return driver.findElements(AllCartItems).size();
    }

    public double GetFinalCost() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FinalCost));

        return Double.parseDouble(driver.findElement(FinalCost).getText());
    }

    public double GetSummFinalCost(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FinalCost));
        List<WebElement> Summ = driver.findElements(By.xpath(".//span[contains(@data-bind,'endSum')]"));
        double k =0;
        for (WebElement x : Summ){
            k = k + Double.parseDouble(x.getText());
        }
        return k;

    }

    public Cart ClickInCart(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(InCart));
        driver.findElement(InCart).click();
        return new Cart(driver);
    }

    public Oformit ClickOformit(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Oformit));
        driver.findElement(Oformit).click();
        return new Oformit(driver);
    }

    public String GetCartName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CartName));
        return driver.findElement(CartName).getText();
    }








}
