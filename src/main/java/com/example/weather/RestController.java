package com.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class RestController {

    @Autowired
    WeatherInfoRepositry repositry;

    @Autowired
    RestTemplate restTemplate;

    static Weather selectedWeather = null;
    static List<Weather> listWeathers = null;

    String[] cityNames = {"Chicago", "New York", "Los Angeles", "San Diego", "Houston" ,
                            "Fort Worth" , "Charlotte" , "Washington", "Detroit"};
    @GetMapping({"/saveData"})
    public String saveData(Model model) {
        if (selectedWeather != null) {
            int a = repositry.saveWeather(selectedWeather);
            if (a == 1) {
                model.addAttribute("outputMessage", "The data saved successfully.");
                model.addAttribute("color", "green");
            } else {
                model.addAttribute("outputMessage", "The data saved failed.");
                model.addAttribute("color", "red");

            }
            System.out.println(a);
        } else {
            model.addAttribute("outputMessage", "There is no data to save.");
            model.addAttribute("color", "red");
        }

        model.addAttribute("listWeathers", listWeathers);
        model.addAttribute("weather", selectedWeather);
        return "index";
    }

    @GetMapping({"/loadData"})
    public String loadData(Model model) {

        listWeathers = repositry.getAllWeatherInformation();
        model.addAttribute("listWeathers", listWeathers);
        model.addAttribute("weather", selectedWeather);
        return "index";
    }

    @RequestMapping(value = "/")
    public String getProductList(Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Weather weather = restTemplate.exchange("http://api.openweathermap.org/data/2.5/weather?q=" + getRandomCity() +"&appid=1e81f75d27109e90541dd77859ca8fc3", HttpMethod.GET, entity, Weather.class).getBody();
        listWeathers = repositry.getAllWeatherInformation();
        model.addAttribute("listWeathers", listWeathers);
        model.addAttribute("weather", weather);
        selectedWeather = weather;
        return "index";
    }

    private String getRandomCity() {
        Random random = new Random();
        return cityNames[random.nextInt(cityNames.length)];
    }
}
