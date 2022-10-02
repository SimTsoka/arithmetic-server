package UnitTests.ServerTests;

import Server.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JSONParserTests {
    @Test
    void testErrorMessageWithName() {
        JSONParser parser = new JSONParser();
        JSONObject actual = parser.errorMessage("Simon", "Test message");

        assertEquals("ERROR", actual.getString("status"));
        assertEquals("Test message", actual.getString("message"));
        assertEquals("", actual.getString("sumType"));
        assertEquals("", actual.getString("sumValue"));
        assertEquals("Simon", actual.getString("name"));
    }

    @Test
    void testErrorMessage() {
        JSONParser parser = new JSONParser();
        JSONObject actual = parser.errorMessage("Test message");
        boolean nameExists = true;

        assertEquals("ERROR", actual.getString("status"));
        assertEquals("Test message", actual.getString("message"));
        assertEquals("", actual.getString("sumType"));
        assertEquals("", actual.getString("sumValue"));

        try {
            actual.getString("name");
        } catch (JSONException e) {
            nameExists = false;
        }

        assertFalse(nameExists);
    }

    @Test
    void testSuccessfulMessage() {
        JSONParser parser = new JSONParser();
        JSONObject actual = parser.successfulMessage("Successful Message", "Simon");

        assertEquals("OK", actual.getString("status"));
        assertEquals("Successful Message", actual.getString("message"));
        assertEquals("", actual.getString("sumType"));
        assertEquals("", actual.getString("sumValue"));
        assertEquals("Simon", actual.getString("name"));
    }
}
