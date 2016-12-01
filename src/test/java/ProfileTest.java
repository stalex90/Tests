import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 29.11.16.
 */
public class ProfileTest {

    static WebDriver driver;
    static OS_Version objOS_Version;
    HomePage objHomePage;
    Profile objProfile;


    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
    }

    @Test(description = "Проверка автоматического заполнения фио")
    public void CheckInputFullname() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearFullName();
        objProfile.InputFullName();
        Thread.sleep(1000);
        Assert.assertEquals(objProfile.GetLastName(),"Иванов");
        Assert.assertEquals(objProfile.GetFirstName(),"Иван");
        Assert.assertEquals(objProfile.GetMiddleName(),"Иванович");
    }

    @Test(description = "Проверка сохранения всех персональных данных - мужской")   //Требуется добавления ассерта после фикса даты рождения
    public void CheckSaveAllInfoMale() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearFullName();
        objProfile.InputFullName();
        objProfile.InputBirthday();
        objProfile.SelectMale();
        objProfile.ClickSave1();
    }
    @Test(description = "Проверка сохранения всех персональных данных - женский")   //Требуется добавления ассерта после фикса даты рождения
    public void CheckSaveAllInfoFemale() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearFullName();
        objProfile.InputFullName();
        objProfile.InputBirthday();
        objProfile.SelectFemale();
        objProfile.ClickSave1();
    }


    @Test(description = "Проверка изменения фио после автоматического заполнения фио")
    public void CheckChangeName() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearFullName();
        objProfile.InputFullName();
        objProfile.InputLastName();
        objProfile.InputFirstName();
        objProfile.InputMiddleName();
        Thread.sleep(1000);
        Assert.assertEquals(objProfile.GetLastName(),"Петров");
        Assert.assertEquals(objProfile.GetFirstName(),"Петр");
        Assert.assertEquals(objProfile.GetMiddleName(),"Петрович");
    }

    @Test(description = "Проверка на пустые поля в персональнных данных")    //Нет валидации пустых полей для д.р и отчества
    public void CheckEmptyWarnings() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearAll();
        objProfile.ClickSave1();
        Assert.assertEquals(objProfile.GetLastNameError(),objProfile.EmptyMsg);
        Assert.assertEquals(objProfile.GetFirstNameError(),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на минимум символов в полях в персональнных данных")    //Нет валидации пустых полей для д.р и отчества
    public void CheckShortWarnings() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearAll();
        objProfile.InputShortLastName();
        objProfile.InputShortFirstName();
        objProfile.InputShortMiddleName();
        objProfile.ClickSave1();
        Assert.assertEquals(objProfile.GetLastNameError(),objProfile.ShortMsg);
        Assert.assertEquals(objProfile.GetFirstNameError(),objProfile.ShortMsg);

    }

    @Test(description = "Проверка на некорректные символов в полях в персональнных данных")    //Нет валидации пустых полей для д.р и отчества
    public void CheckIncorrectWarnings() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClearAll();
        objProfile.InputIncorrectLastName();
        objProfile.InputIncorrectFirstName();
        objProfile.InputIncorrectMiddleName();
        objProfile.ClickSave1();
        Assert.assertEquals(objProfile.GetLastNameError(),objProfile.IncorrectMsg);
        Assert.assertEquals(objProfile.GetFirstNameError(),objProfile.IncorrectMsg);

    }

    @Test(description = "Проверка на полный корректный ввод почтового адреса")
    public void CheckInputAddress() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.InputAllAddress();
        Assert.assertEquals(objProfile.GetSuccesMsg(),objProfile.SuccessMsg);
    }

    @Test(description = "Проверка на появление ошибку региона в почтовом адресе")
    public void CheckRegionError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.RegionError),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на появление ошибку города в почтовом адресе")
    public void CheckCityError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.CityError),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на появление ошибку адреса в почтовом адресе")
    public void CheckAddressError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.AddressError),objProfile.EmptyMsg);
        objProfile.InputIncorrectAddress();
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.AddressError),objProfile.ShortAddressMsg);
    }

    @Test(description = "Проверка на появление ошибку индекса в почтовом адресе")
    public void CheckIndexError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.InputAddress();
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.IndexError),objProfile.EmptyMsg);
        objProfile.InputIncorrectIndex();
        objProfile.ClickSave2();
        Assert.assertEquals(objProfile.GetMsg(objProfile.IndexError),objProfile.ShortIndexMsg);
    }


    @Test(description = "Проверка на добавление нового адреса доставки")
    public void CheckAddAddress() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.InputAllShipAddess();
        Assert.assertEquals(objProfile.GetMsg(objProfile.Success),objProfile.ShipSuccessMsg);
    }

    @Test(description = "Проверка на появление ошибки пустого Страны, Получатель, Телефон")
    public void CheckCountPolPhoneError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.CountryError),objProfile.EmptyMsg);
        Assert.assertEquals(objProfile.GetMsg(objProfile.PoluchatelError),objProfile.EmptyMsg);
        Assert.assertEquals(objProfile.GetMsg(objProfile.ShipPhoneError),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на появление ошибки пустого региона доставки")
    public void CheckShipRegionError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputPoluchatel();
        objProfile.InputShipPhone();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.RegionError),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на появление ошибки пустого города доставки")
    public void CheckShipCityError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputPoluchatel();
        objProfile.InputShipPhone();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.CityError),objProfile.EmptyMsg);
    }

    @Test(description = "Проверка на появление ошибки пустой страны, телефона, получателя в адресе доставки")
    public void CheckShipAddressError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.InputPoluchatel();
        objProfile.InputShipPhone();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.AddressError),objProfile.EmptyMsg);
        objProfile.InputIncorrectAddress();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.AddressError),objProfile.ShortAddressMsg);
    }

    @Test(description = "Проверка на появление ошибок индекса доставки")
    public void CheckShipindexError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.InputPoluchatel();
        objProfile.InputShipPhone();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.InputAddress();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.IndexError),objProfile.EmptyMsg);
        objProfile.InputIncorrectIndex();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.IndexError),objProfile.ShortIndexMsg);
    }

    @Test(description = "Проверка на появление ошибки короткого имени получателя доставки")   //Проверить появление ошибки при вводе цифр????
    public void CheckShortPoluchatelError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.InputShipPhone();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.InputAddress();
        objProfile.InputIndex();
        objProfile.InputShortPoluchatel();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.PoluchatelError),objProfile.ShortMsg);
    }

    @Test(description = "Проверка на появление ошибки телефона доставки")
    public void CheckPhoneError() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickAddNewAddress();
        objProfile.InputPoluchatel();
        objProfile.ClickCountryField();
        objProfile.InputCountry();
        //objProfile.SelectCountry(objProfile.Country);
        objProfile.InputRegion();
        objProfile.InputCity();
        objProfile.InputAddress();
        objProfile.InputIndex();
        objProfile.InputIncorrectShipPhone();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.ShipPhoneError),objProfile.EmptyMsg);
        objProfile.InputShortShipPhone();
        objProfile.ClickShipSave();
        Assert.assertEquals(objProfile.GetMsg(objProfile.ShipPhoneError),objProfile.IncorrectShipPhone);
    }

    @Test(description = "Проверка удаления адреса доставки")
    public void CheckDeleteYes() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickDelete1ShipAddress();
        objProfile.ClickDeleteYes();
        Assert.assertEquals(objProfile.GetMsg(objProfile.Success),objProfile.DeleteSuccessMsg);
    }

    @Test(description = "Проверка появления всплывающего окна удаления адреса доставки")
    public void CheckDeletePopup() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objProfile = new Profile(driver);
        objHomePage.ClickLoginBtn().CompleteLogin();
        objHomePage.ClickProfileIcon();
        objHomePage.ClickProfileBtn();
        objProfile.ClickShipAddress();
        objProfile.ClickDelete1ShipAddress();
        Assert.assertTrue(driver.findElement(objProfile.DeletePopup).isDisplayed());
    }




    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
