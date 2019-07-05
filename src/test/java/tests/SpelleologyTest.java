package tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import context.TestBase;
import models.Spell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utils.UniRestHelper.setObjectMapper;

public class SpelleologyTest extends TestBase {

    @Before
    public void setMapper(){
        setObjectMapper();
    }
    @Test
    public void itShouldDisplayAllTheSpells() throws UnirestException {
        HttpResponse<Spell[]> spellResponse = Unirest.get("https://www.potterapi.com/v1/spells")
                .queryString("key", "$2a$10$f.wBgzvPPpAvJi0D1d1MOOC/uTEqHWqG6tGxfa/i2u.7ob7O9JGla")
                .asObject(Spell[].class);
        List<Spell> spellList = Arrays.asList(spellResponse.getBody());

        driver.get("http://localhost:81/spelleology.php");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.spells li")));

        List<WebElement> displayedSpellList = driver.findElements(By.cssSelector("ul.spells li"));
        List<String> displayedSpellEffects = displayedSpellList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());


        spellList.forEach(spell -> Assert.assertTrue(displayedSpellEffects.contains(spell.getEffect())));



    }

    @Test
    public void getRandomSpellAndEffect() throws UnirestException {

        driver.get("http://localhost:81/spelleology.php");
        //waiting untill spells are present
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.spells li")));
        //saving the spells in a list
        List<WebElement> displayedSpellList = driver.findElements(By.cssSelector("ul.spells li"));
        //gets the size of the list
        int spellListLength = displayedSpellList.size();

        //gets a random spell from the list
        int randomInt = (int)(Math.random() * ((spellListLength ) + 1));
        WebElement spell = driver.findElement(By.xpath("(//li/div[contains(@class, 'inner')])["+randomInt+"]"));
        //gets the name of the spell and clicks it
        String spellEffect = spell.getText();
        spell.click();

        //gets the type of the spell
        String spellType = driver.findElement(By.xpath("//h4")).getText();
        String spellName = driver.findElement(By.xpath("//h2")).getText();
        System.out.println("spell number: "+ randomInt+" spell name: "+spellName+" spell type: "+spellType
        +" spell effect: "+ spellEffect);

        HttpResponse<Spell[]> spellResponse = Unirest.get("https://www.potterapi.com/v1/spells")
                .queryString("key", "$2a$10$f.wBgzvPPpAvJi0D1d1MOOC/uTEqHWqG6tGxfa/i2u.7ob7O9JGla")
                .asObject(Spell[].class);

        List<Spell> spellList = Arrays.asList(spellResponse.getBody());
        Spell ApiSpell=spellList.get(randomInt-1);
        String ApiSpellName = ApiSpell.getSpell();
        String ApiSpellType = ApiSpell.getType();
        String ApiSpellEffect = ApiSpell.getEffect();
        System.out.println("API spell name: "+ ApiSpellName+" API spell type: " + ApiSpellType+
                " API spell effect" + ApiSpellEffect);

        Assert.assertEquals(ApiSpellEffect,spellEffect);
        Assert.assertEquals(ApiSpellName,spellName);
        Assert.assertEquals(ApiSpellType,spellType);


    }
}
