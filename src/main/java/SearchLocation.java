import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchLocation {

    WebDriver driver;
    By searchTextBox = By.className("search-input");
    By searchIcon = By.cssSelector("[data-qa='searchIcon']");

    public SearchLocation(WebDriver driver) {
        this.driver = driver;
    }

    private void enterText(String search) {
        driver.findElement(searchTextBox).sendKeys(search);
    }

    public void search(String searchText) {
        enterText(searchText);
        driver.findElement(searchIcon).click();
    }
}