package pl.oskarpolak.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl.oskarpolak.models.WeatherModel;
import pl.oskarpolak.models.database.DatabaseConnector;
import pl.oskarpolak.models.database.dao.WeatherDao;
import pl.oskarpolak.models.database.dao.impl.WeatherDaoImpl;
import pl.oskarpolak.models.services.WeatherService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    Button buttonHey;

    private WeatherDao weatherDao = new WeatherDaoImpl();
    private WeatherService weatherService = WeatherService.getService();

    public void initialize(URL location, ResourceBundle resources) {

        WeatherModel weatherModel = new WeatherModel(0, "Kraków", 5f);
//        weatherDao.saveWeather(weatherModel);
        weatherService.makeCall("Cracow");


        buttonHey.setOnMouseClicked(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent event) {
               if(event.isAltDown())
               System.out.println("Test");
           }
       });
    }
}
