package com.naveronics.alexa.skill.uvindex;

import com.fasterxml.jackson.databind.JsonNode;
import com.naveronics.alexa.skill.uvindex.service.UvIndexService;
import common.ObjectMother;
import common.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.http.util.TextUtils.isEmpty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionHandlerTest {

    private FunctionHandler functionHandler;

    @Mock
    UvIndexService uvIndexService;
    @Before
    public void setUp() throws Exception {
        uvIndexService = mock(UvIndexService.class);
        functionHandler = new FunctionHandler(uvIndexService);
    }

    @Test
    public void handleRequest() {
        String levelText = ObjectMother.getRandomString();
        String levelDescription = ObjectMother.getRandomString();
        when(uvIndexService.getUvIndexLevelText()).thenReturn(levelText);
        when(uvIndexService.getUvIndexLevelDescription()).thenReturn(levelDescription);

        InputStream inputStream = ObjectMother.getRequestForIntentUvIndexLevelJson("RequestForIntent_UvIndexLevel.json");
        String respondAsString = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            functionHandler.handleRequest(inputStream, outputStream, null);
            respondAsString = outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertFalse(isEmpty(respondAsString));
        System.out.println(respondAsString);
        JsonNode json = TestHelper.createJsonFromString(respondAsString);
        JsonNode ssml = json.findValue("ssml");
        assertNotNull(ssml);
        String ssmlValue = ssml.textValue();
        assertNotNull(ssmlValue);
        assertEquals("<speak>" + levelDescription + "</speak>", ssmlValue);
        JsonNode card = json.findValue("card");
        assertNotNull("Expected node 'card'", card);
        JsonNode content = card.findValue("content");
        assertNotNull(content);
        String contentValue = content.textValue();
        assertNotNull(contentValue);
        assertTrue("'content' has level-text", contentValue.contains(levelText));
    }
}