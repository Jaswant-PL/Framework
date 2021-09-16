import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static ApiUtils.RestCall.apicall;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/CitiDetails.csv"},numLinesToSkip = 1)
    void searchFromForecast(String Cityname,String CityId) {
        boolean titleMatch = driver.getTitle().equals(homepagetitle);
            if (titleMatch) {
                home.searchText(Cityname);
            } else if (!titleMatch){
                home.fromWeatherForecastPage().searchFromForecastPage(Cityname);
                home.fromWeatherForecastPage().search();
            }
            String[] temperature = home.fromWeatherForecastPage().getTemp().split("°");
            int currentWeatherInUI = Math.round(Integer.parseInt(temperature[0]));
            int currentWeatherInApi = Math.round(apicall(CityId));
            assertEquals(currentWeatherInUI, currentWeatherInApi);
    }
}