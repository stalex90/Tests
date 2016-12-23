import org.jsoup.Jsoup;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by user on 23.12.16.
 */
public class GetResponseCode {

    public void CheckResponseCode(String Url) throws IOException {
        int Code = Jsoup.connect(Url).followRedirects(false).ignoreHttpErrors(true).validateTLSCertificates(false).timeout(1000*5).execute().statusCode();
        GmailSend objGmailSend;
        objGmailSend = new GmailSend();

        if (Code != 200){
            String text = "На странице " + Url + " сервер вернул код состояния - " + Code;
            objGmailSend.SendMessage(text, "starodubov2003@mail.ru");
            objGmailSend.SendMessage(text, "law@pokupo.ru");

        }
        System.out.println(Code);

    }



}
