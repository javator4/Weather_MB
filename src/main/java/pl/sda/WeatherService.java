package pl.sda;

import lombok.Builder;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherService {
    private String data = "";
    private String url;
    private String apiKey;
    private String finalURL;

    public WeatherService(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
        this.finalURL = this.url + "?key=" + apiKey + "&q=";
    }

    public String getJSONData(String city) {
        if (this.data.isEmpty()) {
            this.finalURL = this.finalURL + city;
            System.out.println("WYWOŁANIE");
            try {
                this.data = IOUtils.toString(new URL(this.finalURL),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public Current getCityWeather() {

        JSONObject jsonObject = new JSONObject(this.data);

        String temp = jsonObject.getJSONObject("current").get("temp_c").toString();
        String cloud = jsonObject.getJSONObject("current").get("cloud").toString();
        String pressure = jsonObject.getJSONObject("current").get("pressure_mb").toString();
        String humidity = jsonObject.getJSONObject("current").get("humidity").toString();
        String wind_kph = jsonObject.getJSONObject("current").get("wind_kph").toString();
        String wind_dir = jsonObject.getJSONObject("current").get("wind_dir").toString();
        String precip_in = jsonObject.getJSONObject("current").get("precip_in").toString();

        System.out.println(temp);
        System.out.println(cloud);
        System.out.println(pressure);
        System.out.println(humidity);
        System.out.println(wind_kph);
        System.out.println(wind_dir);
        System.out.println(precip_in);

        Current current = Current.builder()
                .temp_c(Double.parseDouble(temp))
                .cloud(Double.parseDouble(cloud))
                .pressure_mb(Double.parseDouble(pressure))
                .humidity(Double.parseDouble(humidity))
                .wind_kph(Double.parseDouble(wind_kph))
                .wind_dir(wind_dir)
                .precip_in(Double.parseDouble(precip_in))
                .build();

        return current;
    }

    public Location getLocation() {
        String stringKey = "location";
        JSONObject jsonObject = new JSONObject(data).getJSONObject(stringKey);

        String lat = jsonObject.get("lat").toString();
        String lon = jsonObject.get("lon").toString();
        String country = jsonObject.get("country").toString();
        String name = jsonObject.get("name").toString();

        Location location = Location.builder()
                .lat(Double.parseDouble(lat))
                .lon(Double.parseDouble(lon))
                .country(country)
                .name(name).build();

        return location;
    }

    public Weather getWeather() {

        JSONObject jsonObject = new JSONObject(this.data);
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