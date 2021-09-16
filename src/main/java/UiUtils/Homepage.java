package UiUtils;

import org.openqa.selenium.WebDriver;

public class Homepage {

    WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchLocation fromHomePage() {
        return new SearchLocation(driver);
    }

    public WeatherForecast fromWeatherForecastPage() {
        return new WeatherForecast(driver);
    }
}