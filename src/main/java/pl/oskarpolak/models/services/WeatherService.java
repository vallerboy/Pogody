package pl.oskarpolak.models.services;

import org.json.JSONObject;
import pl.oskarpolak.models.utils.Config;
import pl.oskarpolak.models.utils.HttpUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private List<WeatherObserver> observers;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();


    private WeatherService() {
        observers = new ArrayList<>();
    }

    public void registerObserver(WeatherObserver observer){
        observers.add(observer);
    }

    private void notifyObservers(WeatherData data) {
        for (WeatherObserver observer : observers) {
            observer.onWeatherUpdate(data);
        }
    }

    public void makeCall(String city){
        executorService.execute(() -> {
            parseJsonData(HttpUtils.makeHttpRequest(Config.APP_URL + city + "&appid=" + Config.APP_ID));
        });
    }

    private void parseJsonData(String text){
        JSONObject root = new JSONObject(text);
        JSONObject main = root.getJSONObject("main");

        int temp = main.getInt("temp");
        String name = root.getString("name");

        WeatherData data = new WeatherData();
        data.setTemp(temp);
        data.setCity(name);


        notifyObservers(data);
    }
}
