import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BaseTest {

    WebDriver driver;
    SearchLocation home;

    @BeforeAll
    void setup() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    void load() {
        driver.get("https://www.accuweather.com/");
        home = new SearchLocation(driver);
    }

    @AfterAll
    void quit() {
        driver.quit();
    }
}
