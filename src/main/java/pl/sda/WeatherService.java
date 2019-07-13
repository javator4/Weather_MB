package pl.sda;


import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import pl.sda.model.Current;

import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.nio.charset.Charset;

@Data
public class WeatherService {
    private String url;
    private String apiKey;
    private String finalURL;


    public WeatherService(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
        this.finalURL = this.url + "?key=" + apiKey + "&q=";

    }

    public void getCityWeather(String city) {
        this.finalURL = this.finalURL + city;
        System.out.println(this.finalURL);

        try {
            String data = IOUtils.toString(new URL(this.finalURL),
                    Charset.forName("UTF-8"));
            JSONObject jsonObject = new JSONObject(data);

            String temp = jsonObject.getJSONObject("current")
            .get("temp_c").toString();
            System.out.println(temp);

            Current current = new Current();
            current.setTemp_c(Integer.parseInt(temp));
            //
            //
            //

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}