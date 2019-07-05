package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankIDTest {


        WebDriver driver;
        String driverPath = "https://www.bankid.no/privat/los-mitt-bankid-problem/test-din-bankid---autentisering/";
    public WebElement expandRootElement(WebElement element) {
        //WebDriver driver = new ChromeDriver();
        WebElement ele = (WebElement) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }
        @Before
        public void setUp() {
            System.out.println("Opening chrome browser");

            driver = new ChromeDriver();
            driver.get(driverPath);
        }

        @Test
        public void verifyPage(){
            String expectedTitle = "TEST DIN BANKID - AUTENTISERING";
            String actualTitle = driver.findElement(By.xpath("//div[contains(@class, 'section section--white')]/h2")).getText();

            Assert.assertEquals(expectedTitle, actualTitle);
        }

        @Test
        public void changeToIframe() throws InterruptedException {
            WebElement iframe1 = driver.findElement(By.xpath("//div[@class='m-iframe inner-container']/iframe"));
            //Switch to the first iframe
            driver.switchTo().frame(iframe1);

            //waiting for the second iframe to be visible
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath("//div[@id='bankid-client']/iframe")));


            WebElement iframe2 = driver.findElement(By.xpath("//div[@id='bankid-client']/iframe"));

            driver.switchTo().frame(iframe2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='full_width_height']"
            )));
            WebElement root1 = driver.findElement(By.xpath("//div[@class='full_width_height']"));
            expandRootElement(root1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='input']"
            )));
            driver.findElement(By.xpath("//div[@class='input']")).sendKeys("123");



        }


    }
