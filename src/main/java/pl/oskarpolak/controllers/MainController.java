package pl.oskarpolak.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.oskarpolak.models.WeatherModel;
import pl.oskarpolak.models.database.DatabaseConnector;
import pl.oskarpolak.models.database.dao.WeatherDao;
import pl.oskarpolak.models.database.dao.impl.WeatherDaoImpl;
import pl.oskarpolak.models.services.WeatherData;
import pl.oskarpolak.models.services.WeatherObserver;
import pl.oskarpolak.models.services.WeatherService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable, WeatherObserver{

    @FXML
    Button buttonWeather;

    @FXML
    Label labelWeather;

    @FXML
    TextField edittextCity;

    private WeatherDao weatherDao = new WeatherDaoImpl();
    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        buttonWeather.setOnMouseClicked(e -> showWeather());

    }

    private void showWeather() {
        weatherService.makeCall(edittextCity.getText());
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        Platform.runLater(() ->  labelWeather.setText("Temperatura: " + data.getTemp()));

        WeatherModel model = new WeatherModel(0, data.getCity(), data.getTemp());
        weatherDao.saveWeather(model);
    }
}
