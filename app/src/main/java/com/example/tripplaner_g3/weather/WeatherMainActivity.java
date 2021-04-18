package com.example.tripplaner_g3.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tripplaner_g3.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class WeatherMainActivity extends AppCompatActivity {
    TextView txtValue;
    TextView txtLocation;
    TextView txtDatetimeCurrent;
    TextView txtHumidity;
    TextView txtWind;
    TextView txtAir;
    TextView txtUV;
    ImageView iconWeatherCurrent;

    public class DownloadTask extends AsyncTask<String, Void , String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                //https://raw.githubusercontent.com/thanhdnh/json/main/products.json
                //10.820400
                //106.634350
                URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=10.820400&lon=106.634350&units=metric&exclude=minutely,hourly&appid=2a20d2a71c5a0737f88861d43949d630");
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
    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;

            URL url;

            HttpURLConnection httpURLConnection;

            InputStream inputStream;

            try {
                Log.i("LINK",strings[0]);
                url = new URL(strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                inputStream = httpURLConnection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity_main);
        txtLocation = findViewById(R.id.txtCity);
        txtDatetimeCurrent = findViewById(R.id.txtDatetimeCurrent);
        txtAir = findViewById(R.id.txtAir);
        txtUV = findViewById(R.id.txtUV);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtWind = findViewById(R.id.txtWind);
        txtValue = findViewById(R.id.txtValue);
        iconWeatherCurrent = findViewById(R.id.iconWeatherCurrent);
        //daily


        String Url = "https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&units=metric&exclude=minutely,hourly&appid=2a20d2a71c5a0737f88861d43949d630";
        DownloadTask downloadTask = new DownloadTask();
        String result = "abc";
        try {
            result = downloadTask.execute(Url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Root data = gson.fromJson(result, (Type) Root.class);

        java.util.Date time =new java.util.Date((long)data.current.dt*1000);
        txtDatetimeCurrent.setText(time.toString().substring(0,11));
        txtValue.setText(""+data.current.temp+"\u2103");
        txtWind.setText(""+data.current.wind_speed);
        txtHumidity.setText(""+data.current.humidity);
        txtUV.setText(""+data.current.uvi);
        txtLocation.setText(""+data.timezone);
        TextView textViewDaily1 = findViewById(R.id.timeDaily1);
        TextView textViewDaily2 = findViewById(R.id.timeDaily2);
        TextView textViewDaily3 = findViewById(R.id.timeDaily3);
        TextView textViewDaily4 = findViewById(R.id.timeDaily4);
        TextView textViewDaily5 = findViewById(R.id.timeDaily5);
        TextView textViewDaily6 = findViewById(R.id.timeDaily6);
        TextView textViewDaily7 = findViewById(R.id.timeDaily7);
        TextView[] listDailyTimeView = {textViewDaily1,textViewDaily2,textViewDaily3,textViewDaily4,textViewDaily5,textViewDaily6,textViewDaily7};

        ImageView iconDaily1 = findViewById(R.id.iconDaily1);
        ImageView iconDaily2 = findViewById(R.id.iconDaily2);
        ImageView iconDaily3 = findViewById(R.id.iconDaily3);
        ImageView iconDaily4 = findViewById(R.id.iconDaily4);
        ImageView iconDaily5 = findViewById(R.id.iconDaily5);
        ImageView iconDaily6 = findViewById(R.id.iconDaily6);
        ImageView iconDaily7 = findViewById(R.id.iconDaily7);
        ImageView[] listIconDailyView = {iconDaily1,iconDaily2,iconDaily3,iconDaily4,iconDaily5,iconDaily6,iconDaily7};
        //daily
        for (int i = 0; i <7;i++){
            java.util.Date timeDaily =new java.util.Date((long)data.daily.get(i+1).dt*1000);
            listDailyTimeView[i].setText(timeDaily.toString().substring(0,11));
            setIconWeather(data.daily.get(i).weather.get(0).icon,listIconDailyView[i]);
        }

        //Set icon weather
        setIconWeather(data.current.weather.get(0).icon,iconWeatherCurrent);

    }
    public void setIconWeather(String iconCode, ImageView iconWeather){
        DownloadImage downloadImage = new DownloadImage();

        String urlIcon = " https://openweathermap.org/img/wn/"+ iconCode +"@2x.png";

        Bitmap bitmap = null;
        try {
            bitmap = downloadImage.execute(urlIcon).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iconWeather.setImageBitmap(bitmap);
    }
}
