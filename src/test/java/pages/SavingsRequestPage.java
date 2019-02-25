package pages;

import models.SavingRequest;
import enumerators.RiskLevel;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SavingsRequestPage {
    @FindBy(id ="oneTimeInvestmentInput")
    private WebElement oneTimeInvestmentInput;

    @FindBy(css = "h1" )
    private WebElement title;

    @FindBy(id = "yearsInput" )
    private WebElement yearsInput;

    @FindBy(id = "fundSelect" )
    private WebElement selectFund;

    @FindBy(xpath = "//div[contains(@class, 'result')]/div[1]/p")
    private WebElement totalIncome;

    @FindBy(xpath = "//div[contains(@class, 'result')]/div[2]/p")
    private WebElement interestIncome;

    @FindBy(css = "div.result")
    private WebElement resultWrapper;

    @FindBy(id = "emailInput")
    private WebElement inputEmail;


    @FindBy(css = "button.btn")
    private WebElement applyForSavingsButton;

    private WebDriver driver;

    public SavingsRequestPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public String getTitle() {
        return title.getText();
    }

    public void selectFund(String fundName) {
        new Select(selectFund).selectByVisibleText(fundName);
    }


    public String getTotalIncome() {
        return resultWrapper.findElement(By.xpath("./div[1]/p")).getText();
    }

    public RiskLevel getRiskLevel() {
        String displayedRisk = resultWrapper.findElement(By.xpath("./div[3]/p")).getText();
        RiskLevel riskLevel = RiskLevel.valueOf(displayedRisk.toUpperCase());
        return riskLevel;
    }
    public String getInterestIncome() {
        return resultWrapper.findElement(By.cssSelector("p")).getText();
    }

    public WebElement getApplyForSavingsButton() {
        return applyForSavingsButton;
    }

    public void inputYears(String years) {
        yearsInput.sendKeys(years);
        yearsInput.sendKeys(Keys.TAB);
    }

    public void inputEmail(String email) {
        inputEmail.sendKeys(email);
        inputEmail.sendKeys(Keys.TAB);
    }

    public void inputInvestment(String oneTimeInvestment) {
        oneTimeInvestmentInput.sendKeys(oneTimeInvestment);
        oneTimeInvestmentInput.sendKeys(Keys.TAB);
    }

    public List<WebElement> getRecentRequestList(){
        return driver.findElements(By.cssSelector("ul.saving-list li"));
    }

    public void enterNewSavingRequestData(SavingRequest request){
        selectFund(request.getFund());
        inputInvestment(request.getOneTimeInvestment());
        inputYears(String.valueOf(request.getYears()));
        inputEmail(request.getEmail());
    }


    public void checkMostRecentSavingRequestData(SavingRequest request) {
        WebElement mostRecentSavingRequest = getRecentRequestList().get(0);

        String actualRequestAmountText = mostRecentSavingRequest.findElement(By.cssSelector("div.amounts"))
                .getText();
        String actualRisk = mostRecentSavingRequest.findElement(By.cssSelector("p.risk")).getText();
        String actualFundDescription = mostRecentSavingRequest
                .findElement(By.cssSelector("p.fund-description"))
                .getText();

        Assert.assertEquals(request.getFund(),actualFundDescription);
        Assert.assertEquals(request.getSavingResult().getRisk().getUiValue(),actualRisk);

        Assert.assertTrue(actualRequestAmountText.contains(request.getSavingResult().getTotalIncome()));
    }
}
