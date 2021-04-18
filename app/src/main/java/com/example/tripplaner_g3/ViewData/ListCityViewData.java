package com.example.tripplaner_g3.ViewData;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.tripplaner_g3.Adapter.HotelAdapter;
import com.example.tripplaner_g3.Model.City;
import com.example.tripplaner_g3.Model.Hotel;
import com.example.tripplaner_g3.Service.CallApiService;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCityViewData extends AsyncTask<String,String,String> {
    private Context context;
    private ListView listView;
    public  static  City[] data;
    public ListCityViewData(Context context, ListView listView){
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected String doInBackground(String... strings) {
        return CallApiService.CallApi("https://6062e82d0133350017fd2160.mockapi.io/api/ListCity");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type) City[].class);
        List<City> listCity = Arrays.asList(data);
        List<String> idList = listCity.stream().map(City::getName).collect(Collectors.toList());
        String[] array = idList.toArray(new String[0]);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, array){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };
        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }

}
