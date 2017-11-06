package pl.oskarpolak.models.services;

import org.json.JSONObject;
import pl.oskarpolak.models.utils.Config;
import pl.oskarpolak.models.utils.HttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    public void makeCall(String city){
        parseJsonData(HttpUtils.makeHttpRequest(Config.APP_URL + city + "&appid=" + Config.APP_ID));
    }

    private void parseJsonData(String text){
        JSONObject root = new JSONObject(text);
        JSONObject main = root.getJSONObject("main");

        int temp = main.getInt("temp");
        System.out.println("Temperatura to: " + temp);
    }
}
