package Functionality;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SwagLabs_Class {

    private WebDriver driver;

    public SwagLabs_Class(WebDriver driver)
    {
        this.driver=driver;
    }
    @FindBy(xpath = "//select[contains(@class,'product_sort_container')]")
    WebElement Sort_Items_By;
    @FindBy(xpath = "//div[@class='inventory_item_name'][contains(.,'Sauce Labs Onesie')]")
    WebElement Cheapest_Item;
    @FindBy(xpath = "(//button[contains(.,'Add to cart')])[1]")
    WebElement Add_To_Cart;
    @FindBy(xpath = "(//button[contains(.,'Add to cart')])[1]")
    WebElement Items_In_Cart;
    @FindBy(xpath = "//button[contains(.,'Open Menu')]")
    WebElement Menu_Item;
    @FindBy(xpath = "//a[contains(.,'Logout')]")
    WebElement Logout;

    public void Sort_Results_By_Price_Low_To_High(String _sortBy)
    {
        WebDriverWait drpSort=new WebDriverWait(driver,20);
        drpSort.until(ExpectedConditions.visibilityOf(Sort_Items_By));

        Select sort=new Select(Sort_Items_By);
        sort.selectByVisibleText(_sortBy);
    }
    public void Verify_Order_Is_By_Price_Low_To_High(String _expectedItem)
    {
        WebDriverWait cheapest=new WebDriverWait(driver,20);
        cheapest.until(ExpectedConditions.visibilityOf(Cheapest_Item));

        Assert.assertEquals(_expectedItem,Cheapest_Item.getText());
    }
    public void Add_Cheapest_To_Cart_And_Verify()
    {
        WebDriverWait addtocart=new WebDriverWait(driver,20);
        addtocart.until(ExpectedConditions.visibilityOf(Add_To_Cart));

        Add_To_Cart.click();

        Assert.assertTrue(Items_In_Cart.isDisplayed());
    }
    public void Logout() throws InterruptedException {
        WebDriverWait menu=new WebDriverWait(driver,20);
        menu.until(ExpectedConditions.visibilityOf(Menu_Item));

        Menu_Item.click();

        WebDriverWait logout=new WebDriverWait(driver,30);
        logout.until(ExpectedConditions.visibilityOf(Logout));
        Logout.click();
    }
}
