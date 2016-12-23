import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 23.12.16.
 */
public class CriticalPageTest {
    GetResponseCode objGetResponseCode;
    GmailSend objGmailSend;
    OS_Version objOS_Version;
    WebDriver driver;
    PokupoHome objPokupoHome;
    PokupoBlog objPokupoBlog;
    Waiters objWaiters;

    @Test(description = "Проверка кодов ответов от страницы")
    public void ProverkaKodovSostoyaniyaServera() throws InterruptedException, IOException {
        objGetResponseCode = new GetResponseCode();
        objGetResponseCode.CheckResponseCode("https://pokupo.ru/");
        objGetResponseCode.CheckResponseCode("https://pokupo.ru/sitemap.xml");
        objGetResponseCode.CheckResponseCode("https://pokupo.ru/robots.txt");
        objGetResponseCode.CheckResponseCode("https://pokupo.ru/blog");
    }

    @Test(description = "Проверка страницы pokupo.ru")
    public void ProverkaPokupoRu_content() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://pokupo.ru/");
        objPokupoHome = new PokupoHome(driver);
        objWaiters = new Waiters(driver);

        try {
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.logo));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.headerBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.videoBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.createBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.casesBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.benefitsBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.portalBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.missionBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.seoBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoHome.footerBlock));
        } finally {
            driver.quit();
        }

    }

    @Test(description = "Проверка страницы pokupo.ru/blog")
    public void ProverkaPokupoBlog_content() {
        objOS_Version = new OS_Version();
        objOS_Version.SetChromeProperty();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://pokupo.ru/blog");
        objPokupoBlog = new PokupoBlog(driver);
        objWaiters = new Waiters(driver);

        try {
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoBlog.headerBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoBlog.eventsBlock));
            Assert.assertTrue(objWaiters.isElementPresentWaiters(objPokupoBlog.footerBlock));
            Assert.assertTrue(objPokupoBlog.getEvents()>0);

        } finally {
            driver.quit();
        }

    }





    @AfterMethod
    public void closebrowser(ITestResult testResult) throws IOException {

        objGmailSend = new GmailSend();
            if (testResult.getStatus() == ITestResult.FAILURE) {
                String text = "Тест " + testResult.getName() + " провален, пожалуйста проверьте вручную";
                objGmailSend.SendMessage(text, "starodubov2003@mail.ru");
                //objGmailSend.SendMessage(text, "law@pokupo.ru");
            }
    }

}
