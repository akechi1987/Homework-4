package com.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WeatherInfoRepositry {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int saveWeather(Weather weather) {
        if (weather == null)
            return -1;
        return jdbcTemplate.update(
                "INSERT INTO `weather` (`city`, `temp`, `humidity`) VALUES (?, ?, ?) ",
                    weather.name, weather.main.temp,weather.main.humidity);
    }

    public List<Weather> getAllWeatherInformation() {
        List<Weather> weathers = new ArrayList<>();
        List<Map<String, Object>> weathersList = jdbcTemplate.queryForList("select * from weather");
        for (Map<String, Object> row : weathersList) {
            Weather weather = new Weather();
            weather.setId((Integer)row.get("id"));
            weather.setName((String)row.get("city"));
            Main main = new Main();
            main.setTemp((Double)row.get("temp"));
            main.setHumidity((Integer)row.get("humidity"));
            weather.setMain(main);
            weathers.add(weather);
        }
        return weathers;
    }
}
