package pl.oskarpolak.models.services;

public interface WeatherObserver {
    void onWeatherUpdate(WeatherData data);
}
