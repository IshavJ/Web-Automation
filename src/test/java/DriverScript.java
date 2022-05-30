import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class DriverScript {
    public static String vProjectName, vProjectURL, vModuleFile, vScenarioFile, vObjectFile, vModuleName, vModRun, vDescription, FilePath;
    public static int m;
    public static WebDriver driver;
    public static ReusableKeyword reusablekeyword;
    public static Properties prop;
    public static String vTCName;

    public static void main(String[] args) throws Throwable {
        FilePath = ReusableFunction.CreateFile();
        ReusableFunction.createColumn(FilePath);
        reusablekeyword = new ReusableKeyword();
        Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\TestData\\ProjectDriver.xlsx");
        int rcnt = xr.getRowCount("Projects");
        System.out.println(rcnt);

        for (int i = 2; i <= rcnt; i++) {
            vProjectName = xr.getCellData("Projects", "ProjectName", i);
            vProjectURL = xr.getCellData("Projects", "ProjectUrl", i);
            vModuleFile = xr.getCellData("Projects", "ModuleFile", i);
            vScenarioFile = xr.getCellData("Projects", "Scenariofile", i);
            vObjectFile = xr.getCellData("Projects", "ObjectFile", i);
            System.out.println(vProjectURL + " " + vProjectName + " " + vModuleFile + " " + vScenarioFile);

            Xls_Reader xrm = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\TestData\\" + vModuleFile);
            Xls_Reader xrs = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\TestData\\" + vScenarioFile);

            int rmodcnt = xr.getRowCount("Projects");
            System.out.println(rmodcnt);

            for (int j = 2; j <= rmodcnt; j++) {
                vModRun = xr.getCellData("vTiger", "Run", j);
                if (vModRun.equalsIgnoreCase("ON")) {
                    vModuleName = xr.getCellData("vTiger", "Modules", j);
                    System.out.println(vModuleName);
                    System.out.println("=========================================");

                    int rtcount = xrm.getRowCount(vModuleName);
                    for (int k = 2; k <= rtcount; k++) {
                        String vTCRun = xrm.getCellData(vModuleName, "Run", k);
                        if (vTCRun.equalsIgnoreCase("ON")) {
                            vTCName = xrm.getCellData(vModuleName, "TCName", k);
                            System.out.println("Testcase need to execute " + vTCName);
                            int flag = 0;
                            int rownum = 0;
                            int rkeywordcnt = xrs.getRowCount(vModuleName);
                            for (m = 2; m <= rkeywordcnt; m++) {
                                String vKeytext = xrs.getCellData(vModuleName, "Keywords", m);
                                if (vKeytext.equals(vTCName)) {
                                    vDescription = xrs.getCellData(vModuleName, "Keywords", m - 1);
                                    flag = 1;
                                    rownum = m;
                                }
                                if ((flag == 1) && (m > rownum)) {
                                    if (vKeytext.contains("//")) {
                                        break;
                                    } else {
                                        String vKeyword = vKeytext.trim();
                                        System.out.println(vKeyword);
                                        GenricMethods gm = new GenricMethods();
                                        gm.keydriver(vKeyword, xrs, m);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
