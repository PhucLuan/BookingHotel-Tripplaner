package com.example.tripplaner_g3.ViewData;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.example.tripplaner_g3.Adapter.HotelAdapter;
import com.example.tripplaner_g3.Model.Hotel;
import com.example.tripplaner_g3.Service.CallApiService;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;

public class HotelViewData extends AsyncTask<String,String,String> {
    private Context context;
    private final GridView gridview;
    public  static  Hotel[] data;
    public HotelViewData(Context context, GridView gridview){
        this.context = context;
        this.gridview = gridview;
    }


    @Override
    protected String doInBackground(String... strings) {
        return CallApiService.CallApi("https://6062e82d0133350017fd2160.mockapi.io/api/ListHotel");
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type)Hotel[].class);
        HotelAdapter adapter = new HotelAdapter(Arrays.asList(data),context);
        gridview.setAdapter(adapter);
    }
}
