import api.WikipediaAPICallerTest;
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
        WikipediaAPICallerTest.class
})

public class TestSuite {
}
