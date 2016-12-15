import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
 * Created by user on 11.11.16.
 */
public class RegistrationSellerTest {

    static WebDriver driver;
    RegSeller1 objRegSeller1;
    RegSeller2 objRegSeller2;
    CabinetSeller objCabinetSeller;
    static OS_Version objOS_Version;

    /*@BeforeSuite
    public static void deleteAllFilesFolder() {
        String path = "/var/lib/jenkins/workspace/Регистрация продавца/src/main/resources/";
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }*/

    @BeforeMethod
    public static void openBrowser(){
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        //System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get("https://dev.pokupo.ru/user/reg_seller/");
        driver.manage().window().maximize();
    }

    @Test(description = "Полная регистрация частного лица")
    public void ChastnoeFullRegistration() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.ChastnoeCompleteRegSeller1();
        objRegSeller2.EmailCompleteRegSeller2();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Полная регистрация компании")
    public void CompanyFullRegistration() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.EmailCompleteRegSeller2();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Краткая регистрация частного лица")
    public void ChastnoeShortRegistration() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.InputEmail();
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        objRegSeller2.InputEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Краткая регистрация компании")
    public void CompanyShortRegistration() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.ClickCompany();
        objRegSeller1.InputEmail();
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        objRegSeller2.InputEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Полная регистрация частного лица без телефона")
    public void ChastnoeFullRegistrationWithoutPhone() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.ChastnoeCompleteRegSellerWithoutPhone1();
        objRegSeller2.InputEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Полная регистрация компании без телефона")
    public void CompanyFullRegistrationWithoutPhone() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objCabinetSeller = new CabinetSeller(driver);
        objRegSeller1.CompanyCompleteRegSellerWithoutPhone1();
        objRegSeller2.InputEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertTrue(driver.findElement(objCabinetSeller.LogoutBtn).isDisplayed());
    }

    @Test(description = "Возвращение назад при активации кода частного лица")
    public void ChastnoeBack()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.ChastnoeCompleteRegSeller1();
        objRegSeller2.ClickBack();
        Assert.assertTrue(driver.findElement(objRegSeller1.Email).isDisplayed());
    }

    @Test(description = "Возвращение назад при активации кода частного лица")
    public void CompanyBack()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.ClickBack();
        Assert.assertTrue(driver.findElement(objRegSeller1.Email).isDisplayed());
    }

    @Test(description = "Ошибка пустого email")
    public void EmptyEmail()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        Assert.assertEquals(objRegSeller1.GetEmailError(),objRegSeller1.EmptyEmailMsg);
    }

    @Test(description = "Ошибка некорректного email")
    public void IncorrectEmail()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.InputIncorrectEmail();
        Assert.assertEquals(objRegSeller1.GetEmailError(),objRegSeller1.IncorrectEmailMsg);
    }

    @Test(description = "Ошибка короткого названия магазина")
    public void ShortName() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.ClickAdditional();
        objRegSeller1.InputShortName();
        Assert.assertEquals(objRegSeller1.GetNameError(),objRegSeller1.ShortNameMsg);
    }

    @Test(description = "Ошибка некорректного названия магазина")
    public void IncorrectName() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.ClickAdditional();
        objRegSeller1.InputIncorrectName();
        Assert.assertEquals(objRegSeller1.GetNameError(),objRegSeller1.IncorrectNameMsg);
    }

    @Test(description = "Ошибка некорректного поддомена")
    public void IncorrectDomain() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.ClickAdditional();
        objRegSeller1.InputIncorrectDomain();
        Assert.assertEquals(objRegSeller1.GetDomainError(),objRegSeller1.IncorrectDomainMsg);
    }

    @Test(description = "Ошибка некорректного адреса сайта")
    public void IncorrectSite() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.ClickAdditional();
        objRegSeller1.InputIncorrectSite();
        Assert.assertEquals(objRegSeller1.GetSiteError(),objRegSeller1.IncorrectSiteMsg);
    }

    @Test(description = "Ошибка некорректного телефона")
    public void IncorrectPhone() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller1.InputEmail();
        objRegSeller1.ClickAdditional();
        objRegSeller1.InputIncorrectPhone();
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        Assert.assertEquals(objRegSeller1.GetPhoneError(),objRegSeller1.IncorrectPhoneMsg);
    }

    @Test(description = "2 Пустых кода")
    public void TwoEmptyCode()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.EmptyCode);
    }

    @Test(description = "Ошибка пустого смс кода с заполненным емаил кодом")
    public void EmptySMSCode()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.InputIncorrectEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.EmptyCode);
    }

    @Test(description = "Ошибка пустого емаил кода с заполненным смс кодом")
    public void EmptyEmailCode()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.InputIncorrectSMSCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.EmptyCode);
    }

    @Test(description = "Ошибка 2 некорректных кодов активации")
    public void TwoIncorrectCode()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.InputIncorrectSMSCode();
        objRegSeller2.InputIncorrectEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.IncorrectCode);
    }

  /*  @Test(description = "Ошибка некорректного емаил кода с заполненным смс кода")
    public void IncorrectEmailCode()  {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.InputIncorrectEmailCode();
        objRegSeller2.InputSMSCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.IncorrectCode);
    }*/

    @Test(description = "Ошибка некорректного смс кода с заполненным емаил кода")
    public void IncorrectSMSCode() throws InterruptedException {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.CompanyCompleteRegSeller1();
        objRegSeller2.InputEmailCode();
        objRegSeller2.InputIncorrectSMSCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.IncorrectSMSCode);
    }

    @Test(description = "Короткая регистрация. Ошибка некорректного емаил кода")
    public void OneIncorrectEmailCode() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.InputEmail();
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        objRegSeller2.InputIncorrectEmailCode();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.IncorrectCode);
    }

    @Test(description = "Короткая регистрация. Ошибка пустого емаил кода")
    public void OneEmptyEmailCode() {
        objRegSeller1 = new RegSeller1(driver);
        objRegSeller2 = new RegSeller2(driver);
        objRegSeller1.InputEmail();
        objRegSeller1.ClickCheckbox();
        objRegSeller1.ClickSubmit();
        objRegSeller2.ClickSubmit();
        Assert.assertEquals(objRegSeller2.GetCodeError(),objRegSeller2.EmptyCode);
    }








    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "/var/lib/jenkins/workspace/Регистрация продавца/src/test/resources" + testResult.getName() + ".jpg";
            FileUtils.copyFile(scrFile, new File(path));
        }
        driver.quit();
    }

}
