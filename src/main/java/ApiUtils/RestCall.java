package ApiUtils;

import static io.restassured.RestAssured.given;

public class RestCall {
    public static float apicall(String City) {
        return given().param("id", City)
                        .param("units","metric")
                        .param("appid", "7fe67bf08c80ded756e598d6f8fedaea")
                        .param("units", "metrics")
                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather")
                        .then()
                        .statusCode(200)
                        .extract().body().path("main.temp");
    }
}