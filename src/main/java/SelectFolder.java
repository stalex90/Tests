import org.openqa.selenium.WebDriver;

/**
 * Created by Администратор on 09.02.2017.
 */
public class SelectFolder {


    public String folderName(){
        String text = System.getProperty("user.dir");
        String s = null;
        if (text.contains("DEV - promodev.pokupo.ru")){
            s = "/var/lib/jenkins/workspace/DEV - promodev.pokupo.ru/";
        }

        if (text.contains("PROD - pokupo.ru")){
            s = "/var/lib/jenkins/workspace/PROD - pokupo.ru/";
        }

        if (text.contains("TEST - pokupotest.pokupo.ru")){
            s = "/var/lib/jenkins/workspace/TEST - pokupotest.pokupo.ru/";
        }

        return s;
    }



}


