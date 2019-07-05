package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Spell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static utils.UniRestHelper.setObjectMapper;

public class PotterApiTest {

    private static final String KEY = "$2a$10$f.wBgzvPPpAvJi0D1d1MOOC/uTEqHWqG6tGxfa/i2u.7ob7O9JGla";

    @Before
    public void setUp(){
        setObjectMapper();
    }

    @Test
    public void itShouldFetchSpells() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getBaseUrl()+"spells")
                .queryString("key", KEY)
                .asJson();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("OK", response.getStatusText());
        int actualSpellCount = response.getBody().getArray().length();
        Assert.assertEquals(152, actualSpellCount);
    }

    @Test
    public void itShouldFetchCharacters() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getBaseUrl() + "characters")
                .queryString("key", KEY)
                .asJson();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("OK", response.getStatusText());
        int actualCharacterCount = response.getBody().getArray().length();
        Assert.assertEquals(195, actualCharacterCount);
    }



    @Test
    public void itShouldFailWhenKeyIsMissing() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getBaseUrl() +"characters")
                .asJson();

        Assert.assertEquals(409, response.getStatus());
        Assert.assertEquals("Must pass API key for request",response.getBody().getObject().get("error"));
    }

    @Test
    public void itShouldFailWhenKeyIsIncorrect() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getBaseUrl() +"spells")
                .queryString("key", "asf")
                .asJson();

        Assert.assertEquals(401, response.getStatus());
        Assert.assertEquals("Unauthorized",response.getStatusText());
        Assert.assertEquals("API Key Not Found",response.getBody().getObject().get("error"));
    }

    private String getBaseUrl() {
        return "https://www.potterapi.com/v1/";
    }

    @Test
    public void itShouldContainDataForEachSpell() throws UnirestException {
        HttpResponse<Spell[]> spellResponse = Unirest.get(getBaseUrl()+"spells")
                .queryString("key", KEY)
                .asObject(Spell[].class);

        List<Spell> spellList = Arrays.asList(spellResponse.getBody());

        spellList.forEach(spell -> {
            Assert.assertFalse(spell.getEffect().isEmpty());
            Assert.assertFalse(spell.getId().isEmpty());
            Assert.assertFalse(spell.getSpell().isEmpty());
            Assert.assertFalse(spell.getType().isEmpty());
        });
    }



}
