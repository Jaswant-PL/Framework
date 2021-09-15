import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest extends BaseTest {

    @Test
    void search() {
        home.search("Nepal Pur, Uttar Pradesh");
    }

}
