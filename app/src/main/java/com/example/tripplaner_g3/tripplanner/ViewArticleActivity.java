package com.example.tripplaner_g3.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplaner_g3.Adapter.HotelAdapter;
import com.example.tripplaner_g3.R;
import com.example.tripplaner_g3.ViewData.HotelViewData;

public class ViewArticleActivity extends AppCompatActivity {
    GridView gridView;
    TextView currentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);
        gridView = findViewById(R.id.mylistHotel);
        currentText = findViewById(R.id.dob_current_text);
        new HotelViewData(getApplicationContext(),gridView).execute();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String check = "";
            }
        });
        currentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = "";
            }
        });
    }
}