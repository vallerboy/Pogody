package pl.oskarpolak.models.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String makeHttpRequest(String url){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL urlClass = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlClass.openConnection();
            InputStream inputStream = connection.getInputStream();

            int read = 0;
            while((read = inputStream.read()) != -1){
                stringBuilder.append((char)read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
