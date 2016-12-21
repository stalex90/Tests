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
 * Created by user on 13.12.16.
 */
public class PaymentTest {
    static WebDriver driver;
    static OS_Version objOS_Version;
    Payment objPayment;


    @BeforeSuite
    public static void deleteAllFilesFolder() {
        objOS_Version = new OS_Version();
        if (objOS_Version.isUnix()) {
            String path = "/var/lib/jenkins/workspace/Оплата заказа/screenshots/";
            for (File myFile : new File(path).listFiles())
                if (myFile.isFile()) myFile.delete();
        }
    }

    @BeforeMethod
    public static void openBrowser() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://promodev.pokupo.ru/payment/1/payment/#//description=%D0%9E%D0%BF%D0%BB%D0%B0%D1%82%D0%B0%20%D0%B7%D0%B0%D0%BA%D0%B0%D0%B7%D0%B0");
        //driver.manage().window().maximize();
    }

    @Test(priority = 0, description = "Проверка оплаты заказа с кошелька z-payment")
    public void CheckPayZpayment() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.InputWallet();
        objPayment.ClickNextBtn2();
        objPayment.InputPayPass();
        objPayment.ClickGoPay();
        objPayment.ClickReturnToShop();
        objPayment.UpdateStatus();
        Assert.assertEquals(objPayment.GetSuccessStatus(),objPayment.StatusMsg);
    }

    /*@Test(priority = 1, description = "Проверка появления ошибки при некорректном кошельке")
    public void CheckIncorrectWalletError() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.ClickNextBtn2();
        Assert.assertEquals(objPayment.GetWalletError(),objPayment.IncorrectWallerMsg);
    }

    @Test(priority = 2, description = "unlock")
    public void ZPAYUnclock1() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.InputWallet();
        objPayment.ClickNextBtn2();
        objPayment.InputPayPass();
        objPayment.ClickGoPay();
        objPayment.ClickReturnToShop();
        objPayment.UpdateStatus();
        Assert.assertEquals(objPayment.GetSuccessStatus(),objPayment.StatusMsg);
    }

    @Test(priority = 3, description = "Проверка появления ошибки при недостаточном балансе")
    public void CheckIncorrectSummforWallet() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("1000");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.InputWallet();
        objPayment.ClickNextBtn2();
        objPayment.InputPayPass();
        objPayment.ClickGoPay();
        Assert.assertEquals(objPayment.GetWalletError(),objPayment.IncorrectSummforWalletMsg);
    }

    @Test(priority = 4, description = "unlock")
    public void ZPAYUnclock2() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.InputWallet();
        objPayment.ClickNextBtn2();
        objPayment.InputPayPass();
        objPayment.ClickGoPay();
        objPayment.ClickReturnToShop();
        objPayment.UpdateStatus();
        Assert.assertEquals(objPayment.GetSuccessStatus(),objPayment.StatusMsg);
    }

    @Test(priority = 5, description = "Проверка появления ошибки при некорректном пароле")
    public void CheckIncorrectPaypas() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("1000");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(0);
        objPayment.ClickGoPay();
        objPayment.InputWallet();
        objPayment.ClickNextBtn2();
        objPayment.InputIncorrectPayPass();
        objPayment.ClickGoPay();
        Assert.assertEquals(objPayment.GetWalletError(),objPayment.IncorrectPayPassMsg);
    }*/



    @Test(description = "Проверка ошибок пустого поля сумма и емаил")
    public void ZpayCheckEmptyWarnings() {
        objPayment = new Payment(driver);
        objPayment.ClickZpay();
        Assert.assertEquals(objPayment.GetEmailError(),objPayment.EmptyErrorMsg);
        Assert.assertEquals(objPayment.GetSummError(),objPayment.EmptyErrorMsg);
    }

    @Test(description = "Проверка ошибок пустого поля сумма и емаил")
    public void ZpayCheckIncorrectWarnings() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("-1");
        objPayment.InputEmail("asd");
        objPayment.ClickZpay();
        Assert.assertEquals(objPayment.GetEmailError(),objPayment.IncorrectEmailMsg);
        Assert.assertEquals(objPayment.GetSummError(),objPayment.IncorrectSummMsg);
    }

    @Test(description = "Проверка возвращение со 2 шага назад и проверка что значения остались")
    public void ZpayCheckBack() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickBackBtn();
        Assert.assertEquals(objPayment.GetEmail(),"pokupomy1@gmail.com");
        Assert.assertEquals(objPayment.GetSumm(),"0.01");
    }



    @Test(description = "Проверка оплаты заказа с кошелька z-payment яндексденьги")
    public void CheckZpayYandex() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("0.01");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(1);
        objPayment.InputYandexWallet();
        objPayment.ClickYandexNext();
        objPayment.ClickYandexGoPay();
        objPayment.SwitchTab();
        //objPayment.ClickPereyti();
        Assert.assertTrue(driver.findElement(objPayment.YandexDomik).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment 3D-Secure")
    public void CheckZpay3D() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(2);
        objPayment.ClickYandexGoPay();
        objPayment.SwitchTab();
        //objPayment.ClickPereyti();
        Assert.assertTrue(driver.findElement(objPayment.Card3DSecureCheck).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment Card RF")
    public void CheckZpayCardRF() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(3);
        objPayment.ClickYandexNext();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment Card Sberbank")
    public void CheckZpayCardSberbank() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(4);
        objPayment.ClickYandexNext();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment Card UA")
    public void CheckZpayCardUA() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(5);
        objPayment.ClickYandexNext();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment Bitcoin")
    public void CheckZpayBitcoin() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(6);
        objPayment.ClickCoinNext();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(objPayment.CoinsRefresh).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment Litecoin")
    public void CheckZpayLitecoin() throws InterruptedException {
        objPayment = new Payment(driver);

        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        Thread.sleep(5000);
        objPayment.ClickPays(7);
        objPayment.ClickCoinNext();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(objPayment.CoinsRefresh).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment интернет банки")
    public void CheckZpayInternetBanks() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(8);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment промсвязьбанк")
    public void CheckZpayPromsvyaz() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(9);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment русский стандарт")
    public void CheckZpayRusstand() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(10);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment втб24")
    public void CheckZpayVTB() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(11);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment тиньков")
    public void CheckZpayTinkof() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(12);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment банки россии")
    public void CheckZpayBankiRussia() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(13);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment сбербанк")
    public void CheckZpaySberbank() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(14);
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка оплаты заказа с кошелька z-payment почта россии")
    public void CheckZpayPochta() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickZpay();
        objPayment.ClickNextBtn();
        objPayment.ClickPays(15);
        objPayment.InputPochta();
        objPayment.ClickNextBtn2();
        Assert.assertTrue(driver.findElement(objPayment.PrintChek).isDisplayed());
    }

    @Test(description = "Проверка к переходу оплаты заказа через intellectmoney")
    public void CheckIntellect() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickIntellectpay();
        objPayment.ClickIntellectNext();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(objPayment.IntellectCheck).isDisplayed());
    }

    @Test(description = "Проверка к переходу оплаты заказа через payu")
    public void CheckPayu() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickPayu();
        objPayment.ClickIntellectNext();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(objPayment.PayUCheck).isDisplayed());
    }

    @Test(description = "Проверка к переходу оплаты заказа через payinout")
    public void CheckPayinout() throws InterruptedException {
        objPayment = new Payment(driver);
        objPayment.InputSumm("50");
        objPayment.InputEmail("pokupomy1@gmail.com");
        objPayment.ClickPayinout();
        objPayment.InputPayinoutPhone();
        objPayment.ClickPayinoutNext();
        objPayment.ClickPayinoutPAY();
        Assert.assertTrue(driver.findElement(objPayment.PayinoutCheck).isDisplayed());
    }









    @AfterMethod

    public void closebrowser(ITestResult testResult) throws IOException {
        if (objOS_Version.isUnix()) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String path = "/var/lib/jenkins/workspace/Оплата заказа/screenshots/" + testResult.getName() + ".jpg";
                FileUtils.copyFile(scrFile, new File(path));
            }
        }
        driver.quit();
    }



}
