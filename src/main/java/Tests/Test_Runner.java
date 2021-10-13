package Tests;

import Functionality.Login_Class;
import Functionality.SwagLabs_Class;
import Helpers.Report_Builder;
import Setup_Class.Setup_Class;
import com.aventstack.extentreports.Status;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test_Runner extends Report_Builder {

    public static String dir = System.getProperty("user.dir");
    private WebDriver driver = Setup_Class.startBrowserOfChoice("https://www.saucedemo.com/", "chrome");
    Login_Class login_class= PageFactory.initElements(driver,Login_Class.class);
    SwagLabs_Class swagLabs_class = PageFactory.initElements(driver,SwagLabs_Class.class);

    ////////////TEST DATA READING///////////////
    public static final String excel = dir+"/src/main/java/Test_Data/Test_Data.xlsx";
    FileInputStream fis = new FileInputStream(excel);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet loginDetails = workbook.getSheetAt(0);
    XSSFSheet sorting=workbook.getSheetAt(1);
    XSSFSheet items=workbook.getSheetAt(2);
    String Username = loginDetails.getRow(1).getCell(0).getStringCellValue();
    String Password = loginDetails.getRow(1).getCell(1).getStringCellValue();
    String SortBy=sorting.getRow(1).getCell(0).getStringCellValue();
    String Items=items.getRow(1).getCell(0).getStringCellValue();
    ////////////END OF TEST DATA READING/////////
    public Test_Runner() throws IOException {
    }
    @Test
    public void Login_With_Standard_User_Test()
    {
        test = extent.createTest("Login_With_Standard_User_Test");
        test.log(Status.PASS,"Enter Username");
        login_class.Enter_Username(Username);
        test.log(Status.PASS,"Enter Password");
        login_class.Enter_Password(Password);
        test.log(Status.PASS,"Click Login Button");
        login_class.Click_Login_Button();
    }
    @Test(priority = 1)
    public void Sort_By_Price_Tests()
    {
        test = extent.createTest("Sort_By_Price_Tests");
        test.log(Status.PASS,"Sort The Items By Price Low To High");
        swagLabs_class.Sort_Results_By_Price_Low_To_High(SortBy);
    }
    @Test(priority =2)
    public void Assert_That_Items_Are_Sorted_By_Correct_Order_Tests()
    {
        test = extent.createTest("Assert_That_Items_Are_Sorted_By_Correct_Order_Tests");
        test.log(Status.PASS,"Verify That Items Are Sorted By Price Low To High");
        swagLabs_class.Verify_Order_Is_By_Price_Low_To_High(Items);
    }
    @Test(priority = 3)
    public void Add_Item_To_Cart_And_Verify_Tests()
    {
        test = extent.createTest("Add_Item_To_Cart_And_Verify_Tests");
        test.log(Status.PASS,"Add Cheapest Item To Cart");
        swagLabs_class.Add_Cheapest_To_Cart_And_Verify();
    }
    @Test(priority =4)
    public void Logout_Tests() throws InterruptedException {
        test = extent.createTest("Logout_Tests");
        test.log(Status.PASS,"Logout");
        swagLabs_class.Logout();
    }
    @AfterSuite
    public void Quit_Tests()
    {
        driver.quit();
    }
}
