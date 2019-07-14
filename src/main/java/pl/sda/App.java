package pl.sda;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class App
{

        private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args)
    {

        logger.info("URUCHOMIENIE APLIKACJI");
        logger.warn("WARNING");
        logger.debug("DEBUG");
        logger.error("ERROR");

        String url =
                "http://api.apixu.com/v1/current.json?key=ec824778fafc4f0197e81613191307&q=Paris";

        WeatherService weatherService = new WeatherService(
                "http://api.apixu.com/v1/current.json",
                "ec824778fafc4f0197e81613191307"
        );
        //  Current current = weatherService.getJSONData("Torun").getCityWeather();
        //Location location = weatherService.getJSONData("Torun").getLocation();

        // System.out.println("LAT: " + location.getLat());
        // System.out.println("LON: " + location.getLon());

        WeatherForecast weatherForecast = new OrgImplementation(weatherService, "Torun");
        WeatherForecast weatherForecast1 = new FasterImplementation(weatherService, "Torun");

        System.out.println(weatherForecast.getWeather());
    }
}