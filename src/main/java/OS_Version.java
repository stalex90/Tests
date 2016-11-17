/**
 * Created by user on 17.11.16.
 */
public class OS_Version {


    public void SetChromeProperty(){
        if(isWindows()){
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            System.out.println(getOSVerion());
        }
        if (isUnix()){
            System.setProperty("webdriver.chrome.driver","/usr/lib/chromium/chromedriver");
            System.out.println(getOSVerion());
        }
    }

    public boolean isWindows(){

        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.indexOf( "win" ) >= 0);

    }

    public  boolean isUnix (){

        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);

    }

    public static String getOSVerion() {
        String os = System.getProperty("os.name");
        return os;
    }
}
