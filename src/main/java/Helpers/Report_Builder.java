package Helpers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report_Builder {

    private ExtentHtmlReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void reportSetup()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/SwagLabs/SwagLabsReport As Of_"+GetCurrentSystemTime()+".html");
        htmlReporter.config().setDocumentTitle("SwagLabs Report");
        htmlReporter.config().setReportName("SwagLabs Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent= new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Hostname","Localhost");
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("Environment","Test_Env");
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed Is " + result.getName());
            test.log(Status.FAIL, "Test Case Failed Is " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped Is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed Is " + result.getName());
        }

    }
    private String GetCurrentSystemTime(){
        String str = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            str= dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        }
        catch(Exception ignored){
        }
        return str;
    }
    @AfterSuite
    public void reportTeardown()
    {
        extent.flush();
    }
}