import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12.10.16.
 */
public class RegistrationTest {

    static WebDriver driver;
    HomePage objHomePage;
    RegistrationPage1 objRegistrationPage1;
    RegistrationPage2 objRegistrationPage2;
    RegistrationPage3 objRegistrationPage3;
    RegistrationPage4 objRegistrationPage4;
    static OS_Version objOS_Version;


    @BeforeMethod
    public static void openBrowser(){
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/shop/1");
        //driver.manage().window().maximize();


    }



    @Test (description = "Проверка регистрации с вводом всех данных")
    public void FullRegistration() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.CompleteRegistration4();
    }

    @Test (description = "Проверка регистрации без ввода необязательных данных")
    public void FullRegistrationWithoutData() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.ClickCheckBox();
        objRegistrationPage4.ClickCheckbox();
    }

    // 1 - этап------------------------------------------------------------------------

    @Test (description = "Проверка появления ошибок для пустых полей на 1 этапе регистрации") ////ПРоверить!!!!!!!!!!!!!
    public void EmptyWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetLoginWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetEmailWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetFirstPswWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetSecondPswWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetCheckboxWarning() ,"Вам необходимо прочитать и принять условия соглашения");

    }

    @Test (description = "Проверка появления ошибки при коротком логине")
    public void ShortLoginWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputShortLogin();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetLoginWarning() ,"Минимум 3 символа");
    }

    @Test (description = "Проверка появления ошибки при некорректном email")
    public void InvalidEmailWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputInvalidEmail();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetEmailWarning() ,"Строка не является адресом электронной почты");
    }

    @Test (description = "Проверка появления ошибки при некорректном номере телефона")
    public void InvalidPhoneWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputLogin();
        objRegistrationPage1.InputInvalidPhone();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetPhoneWarning() ,"Не верный формат телефона");
    }

    @Test (description = "Проверка появления ошибки при коротком пароле")
    public void ShortPasswordWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputShortPassword();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetFirstPswWarning() ,"Пароль должен быть не менее 6 символов");
    }

    @Test (description = "Проверка появления ошибки при не совпадении паролей")
    public void InvalisSecondPswWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputSecondPassword();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetSecondPswWarning() ,"Пароль не совпадает с образцом");
    }

    //2 -этап-------------------------------------------------------------------------------

    @Test (description = "Проверка появления ошибок для пустых полей на 2 этапе регистрации")
    public void EmptyWarningsStep2() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage2.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage2.GetEmailWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage2.GetSMSWarning() ,"Поле обязательно для заполнения");


    }

    @Test (description = "Проверка появления ошибки при неверном коде верификации")
    public void InvalidEmailCodeStep2() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage2.InputInvalidEmailCode();
        objRegistrationPage2.InputInvalidSMSCode();
        Assert.assertTrue(driver.findElement(objRegistrationPage2.VerifyCodeError).isDisplayed());
        Assert.assertEquals(objRegistrationPage2.GetVerifyError() ,"Не удалось выполнить активацию, проверьте правильность указанных кодов");


    }

    //3 -этап------------------------------------------------------------------------------

    @Test (description = "Проверка появления ошибок для пустых полей на 3 этапе регистрации")
    public void EmptyWarningsStep3() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage3.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage3.GetSecondNameWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage3.GetFirstNameWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage3.GetBirthDayWarning() ,"Поле обязательно для заполнения");


    }

    @Test (description = "Проверка появления ошибки при короткой фамилии")
    public void ShortSecondNameWarningStep3() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage3.InputShortSecondName();
        objRegistrationPage3.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage3.GetSecondNameWarning() ,"Минимум 2 символа");
    }

    @Test (description = "Проверка появления ошибки при некорректной фамилии")
    public void InvalidSecondNameWarningStep3() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage3.InputInvalidSecondName();
        objRegistrationPage3.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage3.GetSecondNameWarning() ,"Только буквы латинского или русского алфавита");
    }

    @Test (description = "Проверка появления ошибки при коротком имени")
    public void ShortFirstNameWarningStep3() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage3.InputShortFirstName();
        objRegistrationPage3.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage3.GetFirstNameWarning() ,"Минимум 2 символа");
    }

    @Test (description = "Проверка появления ошибки при некорректном имени")
    public void InvalidFirstNameWarningStep3() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage3.InputInvalidFirstName();
        objRegistrationPage3.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage3.GetFirstNameWarning() ,"Только буквы латинского или русского алфавита");
    }

    //4 - этап -----------------------------------------------------------------------------

    @Test (description = "Проверка появления ошибок для пустого поля страны на 4 этапе регистрации")
    public void EmptyCountryWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetCountryWarning(), "Поле обязательно для заполнения");
    }

    @Test (description = "Проверка появления ошибок для пустого поля регион на 4 этапе регистрации")
    public void EmptyRegionWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetRegionWarning(), "Поле обязательно для заполнения");
    }

    @Test (description = "Проверка появления ошибок для пустого поля город на 4 этапе регистрации")
    public void EmptyCityWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetCityWarning(), "Поле обязательно для заполнения");
    }

    @Test (description = "Проверка появления ошибок для пустого поля адрес на 4 этапе регистрации")
    public void EmptyAddressWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetAddressWarning(), "Поле обязательно для заполнения");
    }

    @Test (description = "Проверка появления ошибок для короткого адреса на 4 этапе регистрации")
    public void ShortAddressWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputShortAddress();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetAddressWarning(), "Адрес должен быть не менее 6 символов");
    }

    @Test (description = "Проверка появления ошибок для пустого поля индекс на 4 этапе регистрации")
    public void EmptyIndexWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputAddress();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetIndexWarning(), "Поле обязательно для заполнения");
    }

    @Test (description = "Проверка появления ошибок для короткого индекса на 4 этапе регистрации")
    public void ShortIndexWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputAddress();
        objRegistrationPage4.InputShortIndex();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetIndexWarning(), "В почтовом индексе должно быть 5 или 6 цифр");
    }

    @Test (description = "Проверка появления ошибок для некорректного индекса на 4 этапе регистрации")
    public void InvalidIndexWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputAddress();
        objRegistrationPage4.InputInvalidIndex();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetIndexWarning(), "Только цифры");
    }





    @AfterMethod
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }





}
