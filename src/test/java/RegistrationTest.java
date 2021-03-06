import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 12.10.16.
 */
public class RegistrationTest {

    static WebDriver driver;
    static HomePage objHomePage;
    static OS_Version objOS_Version;
    private static String URL=System.getProperty("url");
    static SelectFolder objSelectFolder;
    static RegistrationPopup objReg;
    static Waiters objWait;
    static LoginPage objLogin;

    @BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        objSelectFolder = new SelectFolder();
        String s = objSelectFolder.folderName();
        if (objOS_Version.isUnix()) {
            File myPath = new File(s);
            myPath.mkdir();
            String path = s;
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }


    @BeforeMethod
    public static void openBrowser(){
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (objOS_Version.isUnix()) {
            driver.get(URL);}
        if (objOS_Version.isWindows()){
            driver.get("http://promodev56.pokupo.ru/shop/1");
        }
        //driver.manage().window().maximize();
        objHomePage = new HomePage(driver);
        objReg = new RegistrationPopup(driver);
        objWait = new Waiters(driver);



    }

    @Test (description = "Проверка регистрации с email главную страницу")
    public void Registration() throws InterruptedException {
        objHomePage.ClickRegBtn();
        objReg.inputEmailPhone(objReg.emailN);
        objReg.clickSubmit();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(objReg.submit));
        objReg.inputEmailCode();
        objReg.clickCodeSubmit();
        Assert.assertTrue(objWait.isElementPresentWaiters(objHomePage.ProfileIcon));
    }

    @Test (description = "Проверка регистрации с email через окно логина")
    public void RegistrationAtLogin() throws InterruptedException {
        objHomePage.ClickLoginBtn().ClickRegBtn();
        objReg.inputEmailPhone(objReg.emailN);
        objReg.clickSubmit();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(objReg.submit));
        objReg.inputEmailCode();
        objReg.clickCodeSubmit();
        Assert.assertTrue(objWait.isElementPresentWaiters(objHomePage.ProfileIcon));
    }

    @Test (description = "Проверка регистрации с phone")
    public void RegistrationWithPhone() throws InterruptedException {
        objHomePage.ClickRegBtn();
        objReg.inputEmailPhone(objReg.phoneN);
        objReg.clickSubmit();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(objReg.submit));
        objReg.inputPhoneCode();
        objReg.clickCodeSubmit();
        Assert.assertTrue(objWait.isElementPresentWaiters(objHomePage.ProfileIcon));
    }

    @Test (description = "Проверка ошибок поля емаил")
    public void CheckErrorsEmail() throws InterruptedException {
        objHomePage.ClickRegBtn();
        objReg.inputEmailPhone("    ");
        objReg.clickSubmit();
        Assert.assertEquals(objReg.GetError(),objReg.emptyMsg);
        objReg.clearField(objReg.emailORphone);
        objReg.inputEmailPhone("asdfghjkl");
        objReg.clickSubmit();
        Assert.assertEquals(objReg.GetError(),objReg.wrongPhoneEmailMsg);
      }

    @Test (description = "Проверка ошибок поля код")
    public void  CheckErrorsCode() throws InterruptedException {
        objHomePage.ClickRegBtn();
        objReg.inputEmailPhone(objReg.emailN);
        objReg.clickSubmit();
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.invisibilityOfElementLocated(objReg.submit));
        objReg.inputEmptyCode();
        objReg.clickCodeSubmit();
        Assert.assertEquals(objReg.GetError(),objReg.emptyMsg);
        objReg.clearField(objReg.code);
        objReg.inputWrongCode();
        objReg.clickCodeSubmit();
        Assert.assertEquals(objReg.GetError(),objReg.wrongCodeMsg);
    }












   /* @Test (description = "Проверка регистрации с вводом всех данных")
    public void FullRegistration() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.CompleteRegistration4();
        Assert.assertEquals(objRegistrationPage4.GetSuccessMsg(),"Вы успешно зарегистрировались в магазине");
    }

    @Test (description = "Проверка регистрации без ввода необязательных данных") //Проверить!!!!
    public void FullRegistrationWithoutData() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.ClickCheckBox();
        objRegistrationPage4.ClickCheckbox();
        Assert.assertEquals(objRegistrationPage4.GetSuccessMsg(),"Вы успешно зарегистрировались в магазине");
    }

    // 1 - этап------------------------------------------------------------------------

    @Test (description = "Проверка появления ошибок для пустых полей на 1 этапе регистрации")
    public void EmptyWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetLoginWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetEmailWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetFirstPswWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetSecondPswWarning() ,"Поле обязательно для заполнения");
        Assert.assertEquals(objRegistrationPage1.GetCheckboxWarning() ,"Поле обязательно для заполнения");

    }

    @Test (description = "Проверка появления ошибки при коротком логине")
    public void ShortLoginWarningsStep1() {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage1.InputShortLogin();
        objRegistrationPage1.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage1.GetLoginWarning() ,"Минимум 3 символа");
    }

    @Test (description = "Проверка появления ошибки при некорректном emailORphone")
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
        objRegistrationPage2.ClickContinueBtn();
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
        Assert.assertEquals(objRegistrationPage3.GetSecondNameWarning() ,"Недопустимое значение.");
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
        Assert.assertEquals(objRegistrationPage3.GetFirstNameWarning() ,"Недопустимое значение.");
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
        objRegistrationPage4.ClickCountryField();
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
        objRegistrationPage4.ClickCountryField();
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
        objRegistrationPage4.ClickCountryField();
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
        objRegistrationPage4.ClickCountryField();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputShortAddress();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetAddressWarning(), "Минимум 6 символов");
    }

    @Test (description = "Проверка появления ошибок для пустого поля индекс на 4 этапе регистрации")
    public void EmptyIndexWarningStep4() throws InterruptedException {
        objHomePage = new HomePage(driver);
        objRegistrationPage1 = objHomePage.ClickRegBtn();
        objRegistrationPage2 = objRegistrationPage1.CompleteRegistration1();
        objRegistrationPage3 = objRegistrationPage2.CompleteRegistration2();
        objRegistrationPage4 = objRegistrationPage3.CompleteRegistration3();
        objRegistrationPage4.ClickCountryField();
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
        objRegistrationPage4.ClickCountryField();
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
        objRegistrationPage4.ClickCountryField();
        objRegistrationPage4.SelectCountry("Россия");
        objRegistrationPage4.InputRegion();
        objRegistrationPage4.InputCity();
        objRegistrationPage4.InputAddress();
        objRegistrationPage4.InputInvalidIndex();
        objRegistrationPage4.ClickContinueBtn();
        Assert.assertEquals(objRegistrationPage4.GetIndexWarning(), "Только цифры");
    }
    */





    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        objSelectFolder = new SelectFolder();
        String s = objSelectFolder.folderName();
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = s + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }





}
