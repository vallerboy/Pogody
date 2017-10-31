package pl.oskarpolak.models.database.dao.impl;

import pl.oskarpolak.models.WeatherModel;
import pl.oskarpolak.models.database.DatabaseConnector;
import pl.oskarpolak.models.database.dao.WeatherDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }
}
