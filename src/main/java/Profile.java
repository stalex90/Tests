import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 28.11.16.
 */
public class Profile {

    WebDriver driver;

    public Profile(WebDriver driver) {
        this.driver = driver;
    }

    // Профиль--------------------------------------------------------------------------------------------------------------
    By ProfileBtn = By.xpath(".//a[@class='icon__profile']");
    By CountryField = By.xpath(".//*[@id='country_list_chosen']/a");
    By SearchCountryField = By.xpath(".//div[@id='country_list_chosen']/div/div/div/input[contains(@id,'i-')]");
    By Australia = By.xpath(".//*[@id='country_list_chosen']/div/ul/li[2]");
    By SearchWindow = By.xpath(".//*[@id='country_list_chosen']/div");
    By SearchResults = By.xpath(".//*[@class='chosen-results']/li");
    By FirstSearchresultItem = By.xpath(".//*[@id='country_list_chosen']/div/ul/li");
    By RegionField = By.xpath(".//input[@id='region_list']");
    By CityField = By.id("city_list");
    By AddressField = By.id("address");
    By IndexField = By.id("address__postalcode");

    String Country = "Израиль";
    String Region = "Московская область";
    String City = "Москва";
    String Address = "ул. Московская 1 кв.1";
    String Index = "550001";

    //Персональные данные+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    By PersDanBtn = By.xpath(".//span[text()='Персональные данные']");
    By AvatarBtn = By.id("avatar_file");
    By FullName = By.xpath(".//*[@id='fullname']");
    By FullNameResults = By.xpath(".//div[contains(@class,'suggestions-suggestions')]");
    By LastName = By.id("fullname__surname");
    By FirstName = By.id("fullname__name");
    By MiddleName = By.id("fullname__patronymic");
    By BirthDay = By.id("birthDay");
    By Male = By.xpath(".//*[@id='fullname__gender']/label[1]");
    By Female = By.xpath(".//*[@id='fullname__gender']/label[2]");
    By Save1 = By.xpath(".//*[@id='profile_registration_data_form']/fieldset[2]/div[2]/div[2]/div/button");
    By Save2 = By.xpath(".//*[@id='content_pkp']/div/div/div/fieldset[1]/div/div[2]/div/button");
    By Save3 = By.xpath(".//*[@id='content_pkp']/div/div/div/fieldset[2]/div/div[2]/div/div[4]/button");
    By Email = By.id("account__email");
    By PhoneProfile = By.id("phone_profile");
    By DatePicker = By.xpath(".//*[@id='ui-datepicker-div']");

    //Адреса доставки++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    By ShipAddressBtn = By.xpath(".//span[text()='Адреса доставки']");
    By AddAddressBtn = By.xpath(".//button[contains(@data-bind,'ClickAddAddress')]");
    By Poluchatel = By.id("address__addressee");
    By ShipPhone = By.id("address__phone");
    By Checkbox = By.xpath(".//label[@for='makeDefault']");
    By BackBtn = By.xpath(".//button[text()='Назад']");
    By ShipSave = By.xpath(".//*[@id='content_pkp']/div/div/div/fieldset/div/div[2]/div/button[2]");
    By SelectPoint = By.xpath(".//label[@rel='tooltip']");
    By Edit = By.xpath(".//div[@rel='tooltip'][1]");
    By Delete = By.xpath(".//div[@rel='tooltip'][2]");
    By DeletePopup = By.xpath(".//h3[text()='Вы уверены, что хотите удалить адрес?']");
    By DeleteYes = By.xpath(".//button[text()='Да']");
    By DeleteNo = By.xpath(".//button[text()='Да']");



    //Безопасноть+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    By SecurityBtn = By.xpath(".//span[text()='Безопасность']");
    By OldPassword = By.id("p_old_pass");
    By NewPassword = By.id("p_new_pass");
    By RepeatPassword = By.id("p_confirm_pass");
    By SecuritySave = By.xpath(".//*[@id='p_sequrity_form']/button");


    //Ошибки и сообщения-------------------------------------------------------------------------------------------------------------------
    By LastNameError = By.xpath(".//p[contains(@data-bind,'errorLastName')]");
    By FirstNameError = By.xpath(".//p[contains(@data-bind,'errorFirstName')]");
    By MiddleNameError = By.xpath("");
    By BirthDayError = By.xpath(".//p[contains(@data-bind,'errorBirthDay')]");
    By RegionError = By.xpath(".//p[contains(@data-bind,'errorRegion')]");
    By CityError = By.xpath(".//p[contains(@data-bind,'errorCity')]");
    By AddressError = By.xpath(".//p[contains(@data-bind,'errorAddress')]");
    By IndexError = By.xpath(".//p[contains(@data-bind,'errorPostIndex')]");
    By CountryError = By.xpath(".//p[contains(@data-bind,'errorCountry')]");
    By PoluchatelError = By.xpath(".//p[contains(@data-bind,'errorAddressee')]");
    By ShipPhoneError = By.xpath(".//p[contains(@data-bind,'errorContactPhone')]");

    By CountryPlaceholderMsg = By.xpath(".//*[@id='country_list_chosen']/div/div/div/label");


    By Success = By.xpath(".//h3[contains(@class,'icon-success')]");

    String EmptyMsg = "Поле обязательно для заполнения";
    String ShortMsg = "Минимум 2 символа";
    String ShortAddressMsg = "Минимум 6 символов";
    String ShortIndexMsg = "В почтовом индексе должно быть 5 или 6 цифр";
    String IncorrectMsg = "Недопустимое значение.";
    String SuccessMsg = "Данные успешно обновлены";
    String ShipSuccessMsg = "Данные успешно сохранены.";
    String IncorrectShipPhone = "Не верный формат телефона";
    String DeleteSuccessMsg = "Адрес доставки успешно удален.";


    //Getters-------------------------------------------------------------------------------------------------------------------
    public String GetLastName(){
        return driver.findElement(LastName).getAttribute("value");
    }

    public String GetFirstName(){
        return driver.findElement(FirstName).getAttribute("value");
    }

    public String GetMiddleName(){
        return driver.findElement(MiddleName).getAttribute("value");
    }

    public String GetLastNameError() {
        return driver.findElement(LastNameError).getText();
    }

    public String GetFirstNameError() {
        return driver.findElement(FirstNameError).getText();
    }

    public String GetMiddleNameError() {
        return driver.findElement(MiddleNameError).getText();
    }

    public String GetBirthDayError() {
        return driver.findElement(BirthDayError).getText();
    }

    public String GetSuccesMsg() {
        return driver.findElement(Success).getText();
    }

    public String GetMsg(By element){
        return driver.findElement(element).getText();
    }

    //Methods-------------------------------------------------------------------------------------------------------------------

    //Методы для персональных данных++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void InputFullName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FullName));
        driver.findElement(FullName).sendKeys("Иванов Иван Иванович");
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FullNameResults));
        driver.findElement(FullName).sendKeys(Keys.ENTER);


    }

    public void ClearFullName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FullName));
        driver.findElement(FullName).clear();

    }

    public void ClearLastName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LastName));
        driver.findElement(LastName).clear();

    }

    public void ClearFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).clear();

    }

    public void ClearMiddleName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MiddleName));
        driver.findElement(MiddleName).clear();

    }

    public void ClearBirthday(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BirthDay));

        driver.findElement(BirthDay).clear();
        driver.findElement(BirthDay).sendKeys("  ");
        driver.findElement(BirthDay).sendKeys(Keys.ENTER);
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(DatePicker));


    }

    public void ClearAll(){
        ClearFullName();
        ClearLastName();
        ClearFirstName();
        ClearMiddleName();
        ClearBirthday();

    }

    public void InputLastName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LastName));
        driver.findElement(LastName).clear();
        driver.findElement(LastName).sendKeys("Петров");
    }

    public void InputFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).clear();
        driver.findElement(FirstName).sendKeys("Петр");
    }

    public void InputMiddleName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MiddleName));
        driver.findElement(MiddleName).clear();
        driver.findElement(MiddleName).sendKeys("Петрович");
    }

    public void InputBirthday(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(BirthDay));
        driver.findElement(BirthDay).click();
        driver.findElement(BirthDay).sendKeys("11111990");
    }

    public void SelectMale(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Male));
        driver.findElement(Male).click();
    }

    public void SelectFemale(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(Female));
        driver.findElement(Female).click();
    }

    public void ClickSave1(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(Save1));
        driver.findElement(Save1).click();
    }

    public void InputShortLastName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LastName));
        driver.findElement(LastName).clear();
        driver.findElement(LastName).sendKeys("П");
    }

    public void InputShortFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).clear();
        driver.findElement(FirstName).sendKeys("П");
    }

    public void InputShortMiddleName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MiddleName));
        driver.findElement(MiddleName).clear();
        driver.findElement(MiddleName).sendKeys("П");
    }

    public void InputIncorrectLastName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(LastName));
        driver.findElement(LastName).clear();
        driver.findElement(LastName).sendKeys("123");
    }

    public void InputIncorrectFirstName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(FirstName));
        driver.findElement(FirstName).clear();
        driver.findElement(FirstName).sendKeys("123");
    }

    public void InputIncorrectMiddleName(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(MiddleName));
        driver.findElement(MiddleName).clear();
        driver.findElement(MiddleName).sendKeys("123");
    }

    //Методы для почтового адреса и адреса доставки

    public void ClickCountryField(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryField));
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000);");
        driver.findElement(CountryField).click();
    }

    public void InputCountry() throws IOException, InterruptedException {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SearchCountryField));
        driver.findElement(SearchCountryField).click();
        Thread.sleep(2000);
        driver.findElement(SearchCountryField).sendKeys(Country);
        driver.findElement(FirstSearchresultItem).click();
        //(new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(SearchCountryField));
    }




    public void SelectCountry(String country) throws InterruptedException {

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(SearchResults));
        List<WebElement> CountryList = driver.findElements(SearchResults);
        for (WebElement x : CountryList){
            if (x.getText().equals(country)){
                (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CountryPlaceholderMsg));
                x.click();
                return;
            }
        }
    }

    public void InputRegion(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(RegionField));
        driver.findElement(RegionField).clear();
        driver.findElement(RegionField).sendKeys(Region);
    }

    public void InputCity(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(CityField));
        driver.findElement(CityField).clear();
        driver.findElement(CityField).sendKeys(City);
    }



    public void InputAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AddressField));
        driver.findElement(AddressField).clear();
        driver.findElement(AddressField).sendKeys(Address);
        driver.findElement(AddressField).sendKeys(Keys.ENTER);
    }

    public void InputIncorrectAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(AddressField));
        driver.findElement(AddressField).clear();
        driver.findElement(AddressField).sendKeys("123");
        driver.findElement(AddressField).sendKeys(Keys.ENTER);
    }

    public void InputIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexField));
        driver.findElement(IndexField).clear();
        driver.findElement(IndexField).sendKeys(Index);
    }

    public void InputIncorrectIndex(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(IndexField));
        driver.findElement(IndexField).clear();
        driver.findElement(IndexField).sendKeys("12FG");
    }

    public void ClickSave2(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(Save2));
        driver.findElement(Save2).click();
    }

    public void InputAllAddress() throws InterruptedException, IOException {
        ClickCountryField();
        InputCountry();
        //SelectCountry(Country);
        InputRegion();
        InputCity();
        InputAddress();
        InputIndex();
        ClickSave2();

    }

    public void ClickShipAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ShipAddressBtn));
        driver.findElement(ShipAddressBtn).click();
    }

    public void ClickAddNewAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(AddAddressBtn));
        driver.findElement(AddAddressBtn).click();
    }

    public void InputPoluchatel(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(Poluchatel));
        driver.findElement(Poluchatel).sendKeys("Иван");
    }

    public void InputShortPoluchatel(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(Poluchatel));
        driver.findElement(Poluchatel).sendKeys("И");
    }

    public void InputShipPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ShipPhone));
        driver.findElement(ShipPhone).click();
        driver.findElement(ShipPhone).sendKeys("71234567899");
    }

    public void InputIncorrectShipPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ShipPhone));
        driver.findElement(ShipPhone).click();
        driver.findElement(ShipPhone).sendKeys("afasd");
    }

    public void InputShortShipPhone(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ShipPhone));
        driver.findElement(ShipPhone).click();
        driver.findElement(ShipPhone).sendKeys("712345");
    }

    public void ClickShipSave(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(ShipSave));
        driver.findElement(ShipSave).click();

    }

    public void InputAllShipAddess() throws InterruptedException, IOException {
        ClickShipAddress();
        ClickAddNewAddress();
        ClickCountryField();
        InputCountry();
        //SelectCountry(Country);
        InputRegion();
        InputCity();
        InputAddress();
        InputIndex();
        InputPoluchatel();
        InputShipPhone();
        ClickShipSave();

    }

    public void ClickDelete1ShipAddress(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(Delete));
        driver.findElement(Delete).click();
    }

    public void ClickDeleteYes(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(DeleteYes));
        driver.findElement(DeleteYes).click();
    }

    public void ClickDeleteNo(){
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(DeleteNo));
        driver.findElement(DeleteNo).click();
    }





}
