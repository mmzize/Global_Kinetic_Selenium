package Setup_Class;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup_Class {
    public static String dir = System.getProperty("user.dir");

    private static final String Chromedriver =dir + "/src/main/java/Drivers/chromedriver.exe";
    private static final String Firefox = dir + "/src/main/java/Drivers/geckodriver.exe";

    private static WebDriver driver;
    public static WebDriver startBrowserOfChoice(String URL, String BrowserChoice) {
        if(BrowserChoice.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", Chromedriver);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
        }
        else if (BrowserChoice.equalsIgnoreCase("FireFox"))
        {
            System.setProperty("webdriver.gecko.driver", Firefox);
            driver = new FirefoxDriver();
        }
        driver.get(URL);
        return driver;
    }
}