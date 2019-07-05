package context;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    protected WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        //running with selenium server and jenkins
        //Command to start selenium server
        //c:\tmp>java - jar -Dwebdriver.chrome.driver="chromedriver.exe" selenium-server-standalone-3.141.59.jar
        //ChromeOptions chromeOptions = new ChromeOptions();
        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"),chromeOptions);

        //running locally
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

    }


    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
