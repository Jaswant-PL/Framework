package UiUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchLocation {

    WebDriver driver;
    private By searchTextBox = By.className("search-input");
    private By searchIcon = By.className("icon-search");

    public SearchLocation(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String search) {
        driver.findElement(searchTextBox).sendKeys(search);
    }

    public void searchText(String searchText) {
        enterText(searchText);
        driver.findElement(searchIcon).click();
    }

    public WeatherForecast fromWeatherForecastPage() {
        return new WeatherForecast(driver);
    }

}