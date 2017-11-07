package pl.oskarpolak.models.database.dao;

import pl.oskarpolak.models.WeatherModel;

import java.util.List;

public interface WeatherDao {
    void saveWeather(WeatherModel model);
    List<WeatherModel> loadWeather(String city);
    List<WeatherModel> loadWeather();
    List<WeatherModel> loadWeather(float temp);

}
