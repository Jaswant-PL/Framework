import org.openqa.selenium.By;

public enum Weather {
    CURRENTWEATHER(""),
    TODAYWEATHER(""),
    TONIGHTWEATHER("");

    private String weather;

    Weather(String e) {
        weather = e;
    }

    public String getForecast() {
        return weather;
    }
}