package pl.sda;

import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

public class OrgImplementation implements WeatherForecast {
    private WeatherService weatherService;
    private String city;

    public OrgImplementation(WeatherService weatherService, String Torun) {
        this.weatherService = weatherService;
        this.city = city;
    }

    @Override
    public Weather getWeather() {
        JSONObject jsonObject = new JSONObject(this.weatherService.getJSONData(this.city));
        String temp = jsonObject.getJSONObject("current").get("temp_c").toString();
        String lat = jsonObject.getJSONObject("location").get("lat").toString();
        String lon = jsonObject.getJSONObject("location").get("lon").toString();
        String country = jsonObject.getJSONObject("location").get("country").toString();
        String name = jsonObject.getJSONObject("location").get("name").toString();
        String wind_dir = jsonObject.getJSONObject("current").get("wind_dir").toString();
        String precip_in = jsonObject.getJSONObject("current").get("precip_in").toString();

        Weather weather = new Weather();

        Current current = Current.builder()
                .temp_c(Double.parseDouble(temp))
                .wind_dir(wind_dir)
                .precip_in(Double.parseDouble(precip_in))
                .build();

        Location location = Location.builder()
                .lat(Double.parseDouble(lat))
                .lon(Double.parseDouble(lon))
                .country(country)
                .name(name)
                .build();

        weather.setCurrent(current);
        weather.setLocation(location);

        return weather;

    }
}