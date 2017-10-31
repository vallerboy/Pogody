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

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    Button buttonHey;

    WeatherDao weatherDao = new WeatherDaoImpl();

    public void initialize(URL location, ResourceBundle resources) {

        WeatherModel weatherModel = new WeatherModel(0, "Kraków", 5f);
        weatherDao.saveWeather(weatherModel);



       buttonHey.setOnMouseClicked(new EventHandler<MouseEvent>() {
           public void handle(MouseEvent event) {
               if(event.isAltDown())
               System.out.println("Test");
           }
       });
    }
}
