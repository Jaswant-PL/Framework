package UiUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WeatherForecast {

    WebDriver driver;
    private By currentWeather = By.cssSelector("div.cur-con-weather-card__panel div.temp");
    private By searchIcon = By.className("icon-search");

    public WeatherForecast(WebDriver driver) {
        this.driver = driver;
    }

    public String getTemp() {
        return driver.findElement(currentWeather).getText();
    }

    public void searchFromForecastPage(String searchText) {
        new SearchLocation(driver).enterText(searchText);
    }

    public void search() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(searchIcon).click();
    }
}