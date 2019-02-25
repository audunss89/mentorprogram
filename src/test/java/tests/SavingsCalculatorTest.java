package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SavingsCalculatorTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:81/savingscalculator.php");
    }

    @Test
    public void itShouldDisplayTitle(){
    String expectedTitle = "Savings Calculator";
    String actualTitle = driver.findElement(By.cssSelector("h1")).getText();

    Assert.assertEquals(expectedTitle,actualTitle);

    }

    @Test
    public void itShouldCalculateTotalIncome(){
        selectFund("Hoggwart's Fund");
        inputInvestment("5000");

        driver.findElement(By.id("yearsInput")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[1]/p")).getText();

        String incomeText = "kr";
        String actualIncomeText = driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[1]/p")).getText();
        Assert.assertFalse(actualIncomeText.equals(("")));
        Assert.assertTrue(actualIncomeText.contains((incomeText)));
    }


    private void selectFund(String fundName) {
        new Select(driver.findElement(By.id("fundSelect"))).selectByVisibleText(fundName);
    }

    @Test
    public void itShouldCalculateNetIncome(){
        selectFund("Death Star real estate");
        inputInvestment("5000");
        inputYears("10");


        String a = "kr";
        String actualInterstIncome = driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[2]/p")).getText();
        Assert.assertFalse(actualInterstIncome.equals(("")));
        Assert.assertTrue(actualInterstIncome.contains((a)));
    }

    private void inputYears(String years) {
        driver.findElement(By.id("yearsInput")).sendKeys(years);
        driver.findElement(By.id("yearsInput")).sendKeys(Keys.TAB);
    }

    private void inputInvestment(String oneTimeInvestment) {
        driver.findElement((By.id("oneTimeInvestmentInput"))).sendKeys(oneTimeInvestment);
        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys(Keys.TAB);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
