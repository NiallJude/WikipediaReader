import api.APICallerTest;
import files.JSONManagerTest;
import files.PropertyManagerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import wikipedia.PagesTest;
import wikipedia.QueryTest;
import wikipedia.WikipediaTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PagesTest.class,
        QueryTest.class,
        WikipediaTest.class,
        APICallerTest.class,
        PropertyManagerTest.class,
        JSONManagerTest.class
})

public class TestSuite {
}
