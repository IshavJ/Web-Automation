import org.openqa.selenium.By;

public class ReusableKeyword {
    public static void setvalue(String object, String val) throws Throwable {
        System.out.println(object +" and "+val);
        DriverScript.driver.findElement(By.name(object)).clear();
        DriverScript.driver.findElement(By.name(object)).sendKeys(val);
        try {
            if (DriverScript.driver.findElement(By.name(object)).getAttribute("value").equals(val)) {
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, "Value Should be enter as " + val, "Values enter successfuly as " + val, "PASSED");
            } else {
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, "Value Should be enter as " + val, "Values not  enter successfuly as " + val, "FAILED");
                System.out.println("no");
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, "Value Should be enter as " + val, "Value is not entered successfuly as " + val, "FAILED");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void click(String object) throws Throwable {
        if (DriverScript.driver.findElements(By.name(object)).size() == 1) {
            DriverScript.driver.findElement(By.name(object)).click();
            ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, object + " Object should be clicked", object + " object Clicked successfully", "PASSED");
        } else {
            ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, object + " Object should be clicked", object + " object does not Clicked", "Failed");
        }
    }

    public static void clicklnk(String Object) {
        try {
            if (DriverScript.driver.findElements(By.xpath(Object)).size() == 1) {
                System.out.println("Login Clicked");
                DriverScript.driver.findElement(By.xpath(Object)).click();
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Object should be clicked", " object Clicked successfully", "PASSED");
            } else {
                System.out.println("Error Occured");
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Object should be clicked", " object does not exist", "FAILED");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void contains(String Object) {
        DriverScript.driver.findElement(By.xpath(Object)).isDisplayed();
        try {
            if (DriverScript.driver.findElement(By.xpath(Object)).isDisplayed()) {
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Object should be Visible", " object is Visible", "PASSED");
                DriverScript.driver.findElement(By.xpath(Object)).isDisplayed();
            } else {
                ReusableFunction.fgInsertResult(DriverScript.FilePath, DriverScript.vTCName, " Object should be Visible", " object is not visible", "FAILED");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}