package wikipedia;

import com.nialljude.dev.wikipedia.Query;
import com.nialljude.dev.wikipedia.Wikipedia;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WikipediaTest {

    private Query query;
    private Wikipedia wikipediaPage;

    @Before
    public void setUp() {
        query = new Query();
        wikipediaPage = new Wikipedia();
    }

    @Test
    public void queryFieldCanBeSetForTheWikipediaPOJO(){
        wikipediaPage.setQuery(query);
        Query expected = query;
        assertEquals(expected, wikipediaPage.getQuery());
    }

    @Test
    public void queryFieldCanBeRetrievedWithGetterAfterBeingSetForTheWikipediaPOJO(){
        wikipediaPage.setQuery(query);
        Query expected = query;
        Query actual = wikipediaPage.getQuery();
        assertEquals(expected, actual);
    }

    @Test
    public void toStringIsNotEmpty(){
        wikipediaPage.setQuery(query);
        Boolean toStringOutputExists = false;
        String actual = wikipediaPage.toString();
        if (!actual.isEmpty()){
            toStringOutputExists = true;
        }
        assertTrue(toStringOutputExists);
    }

    @Test
    public void toStringIsNotEmptyAndItContainsClassName(){
        wikipediaPage.setQuery(query);
        Boolean toStringStartsWithClassName = false;
        String actual = wikipediaPage.toString();
        String expected = "Wikipedia";
        if (actual.startsWith(expected)){
            toStringStartsWithClassName = true;
        }
        assertTrue(toStringStartsWithClassName);
    }



}
