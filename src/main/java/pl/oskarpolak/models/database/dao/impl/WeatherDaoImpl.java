package pl.oskarpolak.models.database.dao.impl;

import pl.oskarpolak.models.WeatherModel;
import pl.oskarpolak.models.database.DatabaseConnector;
import pl.oskarpolak.models.database.dao.WeatherDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDaoImpl implements WeatherDao {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    @Override
    public void saveWeather(WeatherModel model) {
        PreparedStatement statement = databaseConnector.createStatement
                ("INSERT INTO weather(cityname, temp) VALUES(?, ?);");
        try {
            statement.setString(1, model.getCity());
            statement.setFloat(2, model.getTemp());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WeatherModel> loadWeather(String city) {
        List<WeatherModel> weatherModels = new ArrayList<>();

        PreparedStatement statement = databaseConnector.createStatement(
                "SELECT * FROM weather WHERE cityname = ?"
        );
        try {
            statement.setString(1, city);
            ResultSet set = statement.executeQuery();
            createModels(weatherModels, set);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherModels;
    }

    @Override
    public List<WeatherModel> loadWeather() {
        List<WeatherModel> weatherModels = new ArrayList<>();

        PreparedStatement statement = databaseConnector.createStatement(
                "SELECT * FROM weather"
        );
        try {
            ResultSet set = statement.executeQuery();
            createModels(weatherModels, set);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherModels;
    }

    @Override
    public List<WeatherModel> loadWeather(float temp) {
        List<WeatherModel> weatherModels = new ArrayList<>();

        PreparedStatement statement = databaseConnector.createStatement(
                "SELECT * FROM weather WHERE temp = ?"
        );
        try {
            statement.setFloat(1, temp);
            ResultSet set = statement.executeQuery();
            createModels(weatherModels, set);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherModels;
    }

    private void createModels(List<WeatherModel> weatherModels, ResultSet set) throws SQLException {
        WeatherModel model;
        while (set.next()){
            model = new WeatherModel(set.getInt("id"),
                    set.getString("cityname"),
                    set.getFloat("temp"));
            weatherModels.add(model);
        }
    }


}
