package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SavingsRequestPage;

public class SudokuSolverTest {

    private WebDriver driver;
    private String driverPath = "http://mypuzzle.org/sudoku-for-kids";

    @Before
    public void setUp() {
        System.out.println("Opening chrome browser");

        driver = new ChromeDriver();
        driver.get(driverPath);
    }


    public WebElement expandRootElement(WebElement element) {
        //WebDriver driver = new ChromeDriver();
        WebElement ele = (WebElement) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    @Test
    public void solveSudoku(){


        WebElement element11 = driver.findElement(By.xpath("//*[@id=\"txtC11\"]"));
        WebElement element12 = driver.findElement(By.xpath("//*[@id=\"txtC12\"]"));
        WebElement element13 = driver.findElement(By.xpath("//*[@id=\"txtC13\"]"));
        WebElement element14 = driver.findElement(By.xpath("//*[@id=\"txtC14\"]"));
        WebElement element21 = driver.findElement(By.xpath("//*[@id=\"txtC21\"]"));
        WebElement element22 = driver.findElement(By.xpath("//*[@id=\"txtC22\"]"));
        WebElement element23 = driver.findElement(By.xpath("//*[@id=\"txtC23\"]"));
        WebElement element24 = driver.findElement(By.xpath("//*[@id=\"txtC24\"]"));
        WebElement element31 = driver.findElement(By.xpath("//*[@id=\"txtC31\"]"));
        WebElement element32 = driver.findElement(By.xpath("//*[@id=\"txtC32\"]"));
        WebElement element33 = driver.findElement(By.xpath("//*[@id=\"txtC33\"]"));
        WebElement element34 = driver.findElement(By.xpath("//*[@id=\"txtC34\"]"));
        WebElement element41 = driver.findElement(By.xpath("//*[@id=\"txtC41\"]"));
        WebElement element42 = driver.findElement(By.xpath("//*[@id=\"txtC42\"]"));
        WebElement element43 = driver.findElement(By.xpath("//*[@id=\"txtC43\"]"));
        WebElement element44 = driver.findElement(By.xpath("//*[@id=\"txtC44\"]"));





        //String temp = root1.getAttribute("value");
        //root1.sendKeys("1");

        //System.out.println(temp);


        WebElement root2 = driver.findElement(By.xpath("//*[@id=\"txtC12\"]"));


        String temp2 = root2.getAttribute("value");
        root2.sendKeys("3");


        System.out.println(temp2);
    }




}
