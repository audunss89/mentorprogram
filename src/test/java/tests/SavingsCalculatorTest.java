package tests;

import context.TestBase;
import models.SavingRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SavingsRequestPage;

public class SavingsCalculatorTest extends TestBase {
    private SavingsRequestPage savingsRequestPage;

    @Before
    public void openPage() {
        savingsRequestPage = new SavingsRequestPage(driver);
        savingsRequestPage.openPage();
    }

    @Test
    public void itShouldDisplayTitle() {

        String expectedTitle = "Savings Calculator";
        String actualTitle = savingsRequestPage.getTitle();

        Assert.assertEquals(expectedTitle, actualTitle);

    }

    @Test
    public void itShouldCalculateTotalIncome() {
        savingsRequestPage.selectFund("Hoggwart's Fund");
        savingsRequestPage.inputInvestment("5000");
        savingsRequestPage.inputYears("10");


        String incomeText = "kr";
        String actualIncomeText = savingsRequestPage.getTotalIncome();
        Assert.assertFalse(actualIncomeText.equals(("")));
        Assert.assertTrue(actualIncomeText.contains((incomeText)));
    }

    @Test
    public void itShouldCalculateNetIncome() {
        savingsRequestPage.selectFund("Death Star real estate");
        savingsRequestPage.inputInvestment("5000");
        savingsRequestPage.inputYears("10");


        String a = "kr";
        String actualInterestIncome = savingsRequestPage.getInterestIncome();
        Assert.assertFalse(actualInterestIncome.equals(("")));
        Assert.assertTrue("Total income should contain currency",
                actualInterestIncome.contains((a)));
    }

    @Test
    public void itShouldEnableaddSavingsButton() {
        savingsRequestPage.selectFund("Death Star real estate");
        savingsRequestPage.inputInvestment("5000");
        savingsRequestPage.inputYears("10");
        savingsRequestPage.inputEmail("audunss89@gmail.com");

        Assert.assertTrue(savingsRequestPage.getApplyForSavingsButton().isEnabled());


    }

    @Test
    public void itShouldAddNewSavingsRequestToTheRecentRequestList() {
        SavingRequest request = new SavingRequest(
                "Batman's Cave Development",
                "25000",
                25,
                "audunss89@gmail.com"
        );
        //arrange
        int initialNumberOfRequest = savingsRequestPage.getRecentRequestList().size();
        savingsRequestPage.enterNewSavingRequestData(request);
        //act
        savingsRequestPage.getApplyForSavingsButton().click();
        //assert
        int currentNumberOfRequests = savingsRequestPage.getRecentRequestList().size();
        Assert.assertEquals(initialNumberOfRequest + 1, currentNumberOfRequests);

    }

    @Test
    public void itShouldStoreCorrectResultDataInNewSavingRequest() {
        SavingRequest request = new SavingRequest(
                "Batman's Cave Development",
                "25000",
                25,
                "audunss89@gmail.com"
        );
        savingsRequestPage.enterNewSavingRequestData(request);

        request.getSavingResult().setTotalIncome(savingsRequestPage.getTotalIncome());
        request.getSavingResult().setRisk(savingsRequestPage.getRiskLevel());

        savingsRequestPage.getApplyForSavingsButton().click();


        savingsRequestPage.checkMostRecentSavingRequestData(request);


    }


}
