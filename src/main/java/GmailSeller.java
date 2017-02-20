import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 19.10.16.
 */

public class GmailSeller {

    public static void main(String args[]){
        System.out.println((new GmailSeller()).GetEmailCode());
    }


    public String GetEmailCode() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        String Code = null;
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "PokupoTestReg@gmail.com", "1234Qwer!");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            String Text = stripTags(bp.getContent() + "");
            Code = SkipSpace(Text);
            //System.out.println(Code);
            String CodeS[] = Code.split("");
            int a=0;
            int b=0;
            for (int i=0;i<CodeS.length;i++){
                if (CodeS[i].equals("К") && CodeS[i+1].equals("о") && CodeS[i+2].equals("д") && CodeS[i+3].equals("а")){
                    a = i+13;

                }
                if ((CodeS[i].equals("В") && CodeS[i+1].equals("а") && CodeS[i+2].equals("ш") && CodeS[i+3].equals("л")) ) {
                    b = i-2;

                }
            }
            Code = Code.substring(a,b);

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

    public String SkipSpace(String Text) throws IOException {

        String text = Text;
        text = text.replaceAll("[\\s&&[^\r?\n]]+", " ").replaceAll("(\r\n)+", "\r\n")
                .replaceAll("\n+", "\n");
        return text;
    }



}

