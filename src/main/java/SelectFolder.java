import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 09.02.2017.
 */
public class SelectFolder {


    public String folderName(){
        String text = System.getProperty("user.dir");
        char[] list = text.toCharArray();
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i<list.length;i++){
            String x = list[i] + "";
            if (x.equals("/")){
                indexes.add(i);
            }
        }
        System.out.println(text);
        System.out.println(list);
        System.out.println(indexes);

        String s = text.substring(0,indexes.get(6));



/*
        if (text.contains("DEV - promodev.pokupo.ru")){
            s = "/var/lib/jenkins/workspace/DEV - promodev.pokupo.ru/";
        }

        if (text.contains("PROD - pokupo.ru")){
            s = "/var/lib/jenkins/workspace/PROD - pokupo.ru/";
        }

        if (text.contains("TEST - pokupotest.pokupo.ru")){
            s = "/var/lib/jenkins/workspace/TEST - pokupotest.pokupo.ru/";
        }
*/


        return s;
    }



}


