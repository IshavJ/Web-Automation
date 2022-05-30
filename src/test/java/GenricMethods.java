import java.io.FileInputStream;
import java.util.Properties;

public class GenricMethods {
    public static String vObject, vTitle, vParam1, vParam2;
    public static Properties prop;
    String btn, email, password;

    public void keydriver(String Keyword, Xls_Reader xlscenario, int l) throws Throwable {
        prop = new Properties();
        FileInputStream obj = new FileInputStream(System.getProperty("user.dir") + "/src/test/ObjectRepositery/OR.properties");
        prop.load(obj);
        btn = prop.getProperty("clicklogin");
        email = prop.getProperty("mail");
        password = prop.getProperty("password");
        switch (Keyword) {

            case "Fn_LaunchApp":
                ReusableFunction.LaunchApplication();
                break;

            case "Fn_verifyObjectExist":
                vObject = xlscenario.getCellData(DriverScript.vModuleName, "Object1", l);
                System.out.println(DriverScript.vModuleName);
                System.out.println(vObject);
                ReusableKeyword.contains(vObject);
                break;

            case "Fn_verifyTitle":
                vTitle = DriverScript.driver.getTitle();
                Fn_verifyTitle(vTitle);
                break;

            case "Fn_CloseBrowser":
                ReusableFunction.CloseBrowser();

//            case "Fn_login":
//                //DriverScript1.driver.get("https://demo.opencart.com/index.php?route=account/login");
//                System.out.println("Login Case");
//                try{
//                    //vObject = xlscenario.getCellData(DriverScript1.vModuleName, "Object1", l);
//                    vParam1 = xlscenario.getCellData(DriverScript1.vModuleName, "Param2", l);
//                    vParam2 = xlscenario.getCellData("Login", "Param3", l);
//                    System.out.println(vParam1);
//                    System.out.println(vParam1);
//
//                    login(vParam1, vParam2);
//                }
//                catch (Throwable t){
//                    t.printStackTrace();
//                }
//                break;
        }
    }


//    public void Fn_verifyObjectExist(boolean elem) {
//
//        if (elem == true) {
//            System.out.println("Passed");
//
//        } else {
//            System.out.println("Failed");
//        }
//
//    }

    public void Fn_verifyTitle(String vTitle) {
        try {
            if (DriverScript.driver.getPageSource().contains("Your Store")) {
                System.out.println("Title is visible");
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Title Verified", " Title Verified", "PASSED");
            } else {
                System.out.println("Title is not visible");
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Title not Found", "Titel Not Found", "FAILED");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void login(String vParam1, String vParam2) throws Throwable {
        ReusableKeyword.setvalue(email, vParam1);
        ReusableKeyword.setvalue(password, vParam2);
        ReusableKeyword.clicklnk(btn);
    }
}