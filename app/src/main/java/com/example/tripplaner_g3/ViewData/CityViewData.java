package com.example.tripplaner_g3.ViewData;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.example.tripplaner_g3.Adapter.CityAdapter;
import com.example.tripplaner_g3.Model.City;
import com.example.tripplaner_g3.Service.CallApiService;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;

public class CityViewData extends AsyncTask<String,String,String> {
    private  Context context;
    private final GridView gridview;
    public  static  City[] data;
    public CityViewData(Context context, GridView gridview){
        this.context = context;
        this.gridview = gridview;
    }

    @Override
    protected String doInBackground(String... strings) {
        return CallApiService.CallApi("https://6062e82d0133350017fd2160.mockapi.io/api/touristattraction");
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type) City[].class);
        CityAdapter adapter = new CityAdapter(Arrays.asList(data),context);
        gridview.setAdapter(adapter);
    }

}