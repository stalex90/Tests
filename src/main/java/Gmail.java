import javax.mail.*;
import java.util.Properties;

/**
 * Created by user on 19.10.16.
 */

public class Gmail {

    public static void main(String args[]){
        System.out.println((new Gmail()).GetEmailCode());
    }


    public String GetEmailCode() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        String Code = null;
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "katestarodubova90@gmail.com", "k161xo56rus");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            String Text = stripTags(bp.getContent() + "");
            Code = Text.substring(1535, 1585);
            Code = stripTags(Code);
            System.out.println(Code);
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return Code;
    }

    public String stripTags(String xmlStr) {
        xmlStr = xmlStr.replaceAll("<(.)+?>", "");
        xmlStr = xmlStr.replaceAll("<(\n)+?>", "");
        xmlStr = xmlStr.replaceAll(" ", "");

        return xmlStr;
    }

}

