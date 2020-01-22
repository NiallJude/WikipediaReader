package wikipedia;

import com.nialljude.dev.wikipedia.Pages;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PagesTest {

    private int pageID;
    private String title;
    private String extract;
    private Pages pages;

    @Before
    public void setUp() {
        pageID = 12345;
        title = "testTitle";
        extract = "testExtract";
        pages = new Pages();
    }

    @Test
    public void testTitleCanBeSetOnAPagesObjectUsingLombok() {
        pages.setTitle(title);
        String expected = "testTitle";
        String actual = pages.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testExtractCanBeSetOnAPagesObjectUsingLombok() {
        pages.setExtract(extract);
        String expected = "testExtract";
        String actual = pages.getExtract();
        assertEquals(expected, actual);
    }

    @Test
    public void testPageIDCanBeSetOnAPagesObjectUsingLombok() {
        pages.setPageID(pageID);
        int expected = 12345;
        int actual = pages.getPageID();
        assertEquals(expected, actual);
    }

    @Test
    public void testAllValuesCanBeSetOnAPagesObjectUsingLombok() {
        pages.setPageID(pageID);
        pages.setExtract(extract);
        pages.setTitle(title);
        int expectedID = 12345;
        int actualID = pages.getPageID();
        assertEquals(expectedID, actualID);
        String expectedTitle = "testTitle";
        String actualTitle = pages.getTitle();
        assertEquals(expectedTitle, actualTitle);
        String expectedExtract = "testExtract";
        String actualExtract = pages.getExtract();
        assertEquals(expectedExtract, actualExtract);
    }

    @Test
    public void testAllValuesCanBeSetAndToStringIsNotNullAndWorksOnAPagesObjectUsingLombok() {
        pages.setPageID(pageID);
        pages.setExtract(extract);
        pages.setTitle(title);
        String actual = pages.toString();
        assertNotNull(actual);
    }

    @Test
    public void testAllValuesCanBeSetAndToStringWorksOnAPagesObjectUsingLombok() {
        pages.setPageID(pageID);
        pages.setExtract(extract);
        pages.setTitle(title);
        String actual = pages.toString();
        String expected = "Pages(pageID=12345, title=testTitle, extract=testExtract)";
        assertEquals(expected, actual);
    }


}
