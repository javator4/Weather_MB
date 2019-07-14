package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FasterImplementation implements WeatherForecast {
    private WeatherService weatherService;
    private String city;


    public FasterImplementation(WeatherService weatherService, String Torun) {
        this.weatherService = weatherService;
        this.city = city;
    }

    @Override
    public Weather getWeather() {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = objectMapper.readValue(this.weatherService.getJSONData(this.city), Weather.class);
            objectMapper.writeValue(new File("data.json"), weather);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
