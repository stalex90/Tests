import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24.10.16.
 */
public class RegistrationPage4 {

    WebDriver driver;

    public RegistrationPage4(WebDriver driver) {
        this.driver = driver;
    }

    String Country = "Россия";
    String Region = "Московская область";
    String City = "Москва";
    String Address = "ул. Московская 1 кв.1";
    String Index = "550001";
    public String RandomCountry(){
        List<WebElement> CountryList = driver.findElements(SearchResults);
        int index = 2 + (int)(Math.random() * ((250 - 2) + 1));
        System.out.println(index);
        System.out.println(CountryList.get(index).getText());
        return CountryList.get(index).getText();

    }

    By CountryField = By.xpath(".//*[@id='country_list_chosen']/a");
    By SearchField = By.xpath(".//*[@id='country_list_chosen']/div/div/input");
    By FirstSearchresultItem = By.xpath(".//*[@id='country_list_chosen']/div/ul/li");
    By RegionField = By.id("region_list");
    By CityField = By.id("city_list");
    By AddressField = By.id("address");
    By IndexField = By.id("post_index");
    By CheckBox = By.xpath(".//label[text()='Указать позже']");
    By ContinueBtn = By.xpath(".//a[text()='Продолжить']");
    By SearchResults = By.xpath(".//*[@class='chosen-results']/li");

    By CountryWarning = By.xpath(".//p[contains(@data-bind,'errorCountry')]");
    By RegionWarning = By.xpath(".//p[contains(@data-bind,'errorRegion')]");
    By CityWarning = By.xpath("");
    By AddressWarning = By.xpath(".//p[contains(@data-bind,'errorAddress')]");
    By IndexWarning = By.xpath(".//p[contains(@data-bind,'errorPostIndex')]");
    By Success = By.xpath(".//h3[contains(@class,'icon-success')]");

    //Getters------------------------------------------------------------------

    public String GetSuccessMsg(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Success));
        return driver.findElement(Success).getText();
    }
    public String GetCountryWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryWarning));
        return driver.findElement(CountryWarning).getText();
    }

    public String GetRegionWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegionWarning));
        return driver.findElement(RegionWarning).getText();
    }

    public String GetCityWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CityWarning));
        return driver.findElement(CityWarning).getText();
    }

    public String GetAddressWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AddressWarning));
        return driver.findElement(AddressWarning).getText();
    }

    public String GetIndexWarning(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexWarning));
        return driver.findElement(IndexWarning).getText();
    }



    //Methods------------------------------------------------------------------
    public void ClickCountryField(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryField));
        driver.findElement(CountryField).click();
    }

    public void SelectCountry(String country) throws InterruptedException {

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SearchField));
        driver.findElement(SearchField).click();
        Thread.sleep(2000);
        driver.findElement(SearchField).sendKeys(RandomCountry());
        driver.findElement(FirstSearchresultItem).click();
        ClickCountryField();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SearchField));
        driver.findElement(SearchField).sendKeys(Keys.ENTER);
    }

    public void InputRegion(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegionField));
        driver.findElement(RegionField).sendKeys(Region);
    }

    public void InputCity(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CityField));
        driver.findElement(CityField).sendKeys(City);
    }

    public void InputAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AddressField));
        driver.findElement(AddressField).sendKeys(Address);
        driver.findElement(AddressField).sendKeys(Keys.ENTER);

    }

    public void InputShortAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AddressField));
        driver.findElement(AddressField).sendKeys("te");
        driver.findElement(AddressField).sendKeys(Keys.ENTER);

    }

    public void InputIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexField));
        driver.findElement(IndexField).sendKeys(Index);
    }

    public void InputShortIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexField));
        driver.findElement(IndexField).sendKeys("123");
    }

    public void InputInvalidIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexField));
        driver.findElement(IndexField).sendKeys("123asd");
    }

    public void ClickCheckbox(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryField));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CheckBox));
        driver.findElement(CheckBox).click();
    }

    public void ClickContinueBtn(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryField));
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(ContinueBtn));
        driver.findElement(ContinueBtn).click();
    }

    public void CompleteRegistration4() throws InterruptedException {
        ClickCountryField();
        SelectCountry(Country);
        InputRegion();
        InputCity();
        InputAddress();
        InputIndex();
        ClickContinueBtn();

    }



}
