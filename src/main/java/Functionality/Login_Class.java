package Functionality;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Class {
    private WebDriver driver;

    public Login_Class(WebDriver driver)
    {
        this.driver=driver;
    }
    @FindBy(xpath = "//input[contains(@placeholder,'Username')]")
    WebElement Username;
    @FindBy(xpath = "//input[contains(@placeholder,'Password')]")
    WebElement Password;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement Login_Button;

    public void Enter_Username(String _un)
    {
        WebDriverWait un=new WebDriverWait(driver,20);
        un.until(ExpectedConditions.visibilityOf(Username));
        Username.sendKeys(_un);
    }
    public void Enter_Password(String _pw)
    {
        WebDriverWait pw=new WebDriverWait(driver,20);
        pw.until(ExpectedConditions.visibilityOf(Password));
        Password.sendKeys(_pw);
    }
    public void Click_Login_Button()
    {
        WebDriverWait login=new WebDriverWait(driver,20);
        login.until(ExpectedConditions.visibilityOf(Login_Button));
        Login_Button.click();
    }
}
