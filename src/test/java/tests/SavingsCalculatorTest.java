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
import pages.SavingsRequestPage;

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
        SavingsRequestPage savingsRequestPage = new SavingsRequestPage(driver);
        String expectedTitle = "Savings Calculator";
        String actualTitle = savingsRequestPage.getTitle();

        Assert.assertEquals(expectedTitle,actualTitle);

    }

    @Test
    public void itShouldCalculateTotalIncome(){
        SavingsRequestPage savingsRequestPage = new SavingsRequestPage(driver);

        savingsRequestPage.selectFund("Hoggwart's Fund");
        savingsRequestPage.inputInvestment("5000");
        savingsRequestPage.inputYears("10");


        String incomeText = "kr";
        String actualIncomeText = savingsRequestPage.getTotalIncome();
        Assert.assertFalse(actualIncomeText.equals(("")));
        Assert.assertTrue(actualIncomeText.contains((incomeText)));
    }

    @Test
    public void itShouldCalculateNetIncome(){
        SavingsRequestPage savingsRequestPage = new SavingsRequestPage(driver);
        savingsRequestPage.selectFund("Death Star real estate");
        savingsRequestPage.inputInvestment("5000");
        savingsRequestPage.inputYears("10");


        String a = "kr";
        String actualInterestIncome = savingsRequestPage.getInterestIncome();
        Assert.assertFalse(actualInterestIncome.equals(("")));
        Assert.assertTrue(actualInterestIncome.contains((a)));
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
