package com.example.tripplaner_g3.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallApiService {
    public static String CallApi(String _url){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine())!= null){
                buffer.append(line+"\n");
            }
            return buffer.toString();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.disconnect();
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
