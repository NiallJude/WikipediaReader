package files;

import com.nialljude.dev.files.JSONManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class JSONManagerTest {

    private JSONManager jsonManager;

    @Before
    public void setUp(){
        jsonManager = new JSONManager();
    }

    @Test
    public void getMapOfFormattedValuesFromJSONReturnsAListOfFiveItemsWhenNIsEqualToFive() {
        String testDataPath = "src/test/java/com/nialljude/dev/files/TestData.json";
        Map<Integer, String> testMap = jsonManager.getMapOfFormattedValuesFromJSON(testDataPath);
        int actual = testMap.size();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void getMapOfFormattedValuesFromJSONReturnsAListContainingQuestionsAsAString() {
        String testDataPath = "src/test/java/com/nialljude/dev/files/TestData.json";
        Map<Integer, String> testMap = jsonManager.getMapOfFormattedValuesFromJSON(testDataPath);
        boolean exists = false;
        String expectedValue = "questions";
        if (testMap.containsValue(expectedValue)){
            exists = true;
        }
        assertTrue(exists);
    }

    @Test
    public void getMapOfFormattedValuesFromJSONReturnsAListContainingStackAsAString() {
        String testDataPath = "src/test/java/com/nialljude/dev/files/TestData.json";
        Map<Integer, String> testMap = jsonManager.getMapOfFormattedValuesFromJSON(testDataPath);
        boolean exists = false;
        String expectedValue = "stack";
        if (testMap.containsValue(expectedValue)){
            exists = true;
        }
        assertTrue(exists);
    }

    @Test
    public void getMapOfFormattedValuesFromJSONDoesNotContainQUESTIONSBecauseRegexRemovedCases() {
        String testDataPath = "src/test/java/com/nialljude/dev/files/TestData.json";
        Map<Integer, String> testMap = jsonManager.getMapOfFormattedValuesFromJSON(testDataPath);
        boolean exists = false;
        String expectedValue = "QUESTIONS";
        if (testMap.containsValue(expectedValue)){
            exists = true;
        }
        assertFalse(exists);
    }
}
