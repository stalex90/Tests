import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Администратор on 13.03.2017.
 */
public class RegistrationPopup {
    WebDriver driver;
    public RegistrationPopup(WebDriver driver) {
        this.driver = driver;
    }

    int a1 = 1000000000 + (int)(Math.random()*((2000000000-1000000000)+1));
    String a = Integer.toString(a1);
    String emailN = "pokupotest+" + a + "@gmail.com";
    String passwordN = "1234qwer";
    String phoneN = "7" + a;

    By emailORphone = By.xpath(".//*[@name='email']");
    By submit = By.xpath(".//*[text()='Получить пароль']");
    By code = By.xpath("//form[@data-bind='submit: Submit']/div/div/input[contains(@class,'inline-input')]");
    By codeSubmit = By.xpath(".//button[text()='Отправить код']");

    By error = By.xpath(".//div[@class='error__message']/p");
    String emptyMsg = "Поле обязательно для заполнения";
    String wrongCodeMsg = "Неверный код";
    String wrongPhoneEmailMsg = "Недопустимое значение.";
    String alreadyMsg = "Аккаунт для этого почтового ящика уже существует, рекомендуем пройти процедуру восстановления доступа. Восстановить доступ";




    public String GetError(){
        return driver.findElement(error).getText();
    }

    public void inputEmailPhone(String a){
        driver.findElement(emailORphone).sendKeys(a);
    }

    public void clickSubmit(){
        driver.findElement(submit).click();
    }

    public void inputEmailCode() throws InterruptedException {
        Thread.sleep(30000);
        String Code = new Gmail().GetEmailCode();
        System.out.println(Code);
        driver.findElement(code).sendKeys(Code);
    }

    public void inputPhoneCode(){

        driver.findElement(code).sendKeys("46476426342423");
    }

    public void inputEmptyCode(){
        driver.findElement(code).sendKeys("     ");
    }

    public void inputWrongCode(){
        driver.findElement(code).sendKeys("asdasd");
    }



    public void clickCodeSubmit(){
        driver.findElement(codeSubmit).click();
    }

    public void clearField(By a){
        driver.findElement(a).clear();
    }






}
