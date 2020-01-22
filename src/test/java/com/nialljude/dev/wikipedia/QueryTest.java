package wikipedia;

import com.nialljude.dev.wikipedia.Pages;
import com.nialljude.dev.wikipedia.Query;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNull;

public class QueryTest {

    private Map<String, Pages> pages;
    private Query query;

    @Before
    public void setUp() {
        query = new Query();
    }

    @Test
    public void getterWorksForTheQueryObjectButTheObjectWillBeNullDueToNoSetterBeingRequiredInThePOJO() {
        pages = query.getPages();
        assertNull(pages);
    }


}
