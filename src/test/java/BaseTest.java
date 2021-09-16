import UiUtils.SearchLocation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class BaseTest {

    public final static String homepagetitle = "Local, National, & Global Daily Weather Forecast | AccuWeather";

    WebDriver driver;
    SearchLocation home;

    @BeforeAll
    void setup() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.accuweather.com/");
        home = new SearchLocation(driver);
    }

    @AfterAll
    void quit() {
        driver.quit();
    }
}
