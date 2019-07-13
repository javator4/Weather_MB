package pl.sda;

import pl.sda.model.Current;
import pl.sda.WeatherService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

//        http://api.apixu.com/v1/current.json?
//        key=ec824778fafc4f0197e81613191307&q=Torun

        WeatherService weatherService = new WeatherService("http://api.apixu.com/v1/current.json",
                "ec824778fafc4f0197e81613191307");

        weatherService.getCityWeather("Torun");

    }
}