package files;

import com.nialljude.dev.files.PropertyManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PropertyManagerTest {

    private PropertyManager propertyManager;
    Properties properties;

    @Before
    public void setUp() {
        propertyManager = new PropertyManager();
        properties  = propertyManager.getProperties();
    }

    @Test
    public void aGivenPropertyCanBeRetrieved(){
        String expected = "https";
        String actual = properties.getProperty("url.scheme");
        assertEquals(expected, actual);
    }

    @Test
    public void aGivenPropertyCanBeRetrievedAndParsedAsInt(){
        int expected = 5;
        int actual = Integer.parseInt(properties.getProperty("page.wordsToDisplay"));
        assertEquals(expected, actual);
    }

    @Test
    public void aGivenPropertyCanBeSubstitutedIntoAString(){
        String replace= "test";
        String replaceWith=properties.getProperty("url.scheme");
        String body = "Thistestpasses";
        String actual = body.replace(replace, replaceWith);
        String expected = "Thishttpspasses";
        assertEquals(expected, actual);
    }
}
