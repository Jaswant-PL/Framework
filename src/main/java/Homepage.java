import org.openqa.selenium.WebDriver;

public class Homepage {

    WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchLocation searchforlocation() {
//        searchText();
        return new SearchLocation(driver);
    }

}