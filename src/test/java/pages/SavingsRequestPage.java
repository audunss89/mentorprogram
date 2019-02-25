package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
    public String getInterestIncome() {
        return resultWrapper.findElement(By.cssSelector("p")).getText();
    }

    public void inputYears(String years) {
        yearsInput.sendKeys(years);
        yearsInput.sendKeys(Keys.TAB);
    }

    public void inputInvestment(String oneTimeInvestment) {
        oneTimeInvestmentInput.sendKeys(oneTimeInvestment);
        oneTimeInvestmentInput.sendKeys(Keys.TAB);
    }


}
