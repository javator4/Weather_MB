package pl.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Current {

    private int last_updated_epoch;
    private String last_updated;
    private double temp_c;
    private double temp_f;
    private double is_day;
    private Condition condition;
    private double wind;
    private double wind_mph;
    private double wind_kph;
    private double wind_degree;
    private String wind_dir;
    private double pressure_mb;
    private double pressure_in;
    private double precip_mm;
    private double precip_in;
    private double humidity;
    private double cloud;
    private double feelslike_c;
    private double feelslike_f;
    private double vis_km;
    private double vis_miles;
    private int uv;
    private double gust_mph;
    private double gust_kph;
}