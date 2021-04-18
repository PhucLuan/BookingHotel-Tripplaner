package com.example.tripplaner_g3.tripplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplaner_g3.R;

import java.util.ArrayList;
import java.util.List;

import vn.demonganluong.ui.activity.PaymentMainActivity;


public class Booking extends AppCompatActivity {
    private TextView bookingtext;
    private TextView name2;
    private Button booknow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        LinearLayout l1= findViewById(R.id.l1);
        name2= findViewById(R.id.name);
        TextView hotelbookingname1= findViewById(R.id.hotelbookingname1);
        TextView addressbooking1= findViewById(R.id.addressbooking1);
        TextView datesbooking1= findViewById(R.id.datesbooking1);
        TextView pricebooking1= findViewById(R.id.pricebooking1);
        Intent intent = getIntent();
        Intent intent1 = getIntent();
        booknow= findViewById(R.id.booknow1);
        final Bundle bundle1=intent1.getExtras();
        final Bundle bundle = intent.getExtras();
        bookingtext = findViewById(R.id.bookingtext);
        bookingtext.setVisibility(View.VISIBLE);
        booknow.setVisibility(View.VISIBLE);

        if(bundle!=null ) {
            String name = preferences.getString("Name", "empty");
            String name1 = (String) bundle.get("Name");
            String order=(String)bundle.get("order");
            if(order.equals("yes")){
            if(name.equals("empty")){
                bookingtext.setVisibility(View.VISIBLE);
                booknow.setVisibility(View.VISIBLE);
                bookingtext.setText(name1 + ", Start booking to unlock rooms");
            }else{
                l1.setVisibility(View.VISIBLE);
            String hotelname = preferences.getString("hotelname", "");
            String today = preferences.getString("Today", "");
            String tomorrow = preferences.getString("Tomorrow", "");
            String noofrooms = preferences.getString("noofrooms", "");
            String noofadults = preferences.getString("noofadults", "");
            String img1 = preferences.getString("img1", "");
            String img2 = preferences.getString("img2", "");
            String img3 = preferences.getString("img3", "");
            String img4 = preferences.getString("img4", "");
            String address = preferences.getString("address", "");
            String price = preferences.getString("price", "");
            name2.setText(name);
            hotelbookingname1.setText(hotelname);
            addressbooking1.setText(address);
            pricebooking1.setText(price);
            datesbooking1.setText(today + " - " + tomorrow + " - " + noofrooms + " - " + noofadults );
        }
        }}
        if(bundle1!=null){
            String order = (String)bundle1.get("order");
            String name1=(String)bundle1.get("Name1");
            String hotelname=(String)bundle1.get("hotelname");
            String today = (String) bundle1.get("Today");
            String tomorrow = (String) bundle1.get("Tomorrow");
            String noofrooms = (String) bundle1.get("noofrooms");
            String noofadults = (String) bundle1.get("noofadults");
            final String img1 = (String) bundle1.get("img1");
            final String img2 = (String) bundle1.get("img2");
            final String img3 = (String) bundle1.get("img3");
            final String img4 = (String) bundle1.get("img4");
            String address = (String) bundle1.get("address");
            final String price = (String) bundle1.get("price");
            if(order.equals("yes")) {
                editor.putString("Name", name1);
                editor.putString("hotelname", hotelname);
                editor.putString("Today", today);
                editor.putString("Tomorrow", tomorrow);
                editor.putString("noofrooms", noofrooms);
                editor.putString("noofadults", noofadults);
                editor.putString("img1", img1);
                editor.putString("img2", img2);
                editor.putString("img3", img3);
                editor.putString("img4", img4);
                editor.putString("address", address);
                editor.putString("price", price);
                editor.apply();
                name2.setText(name1);
                hotelbookingname1.setText(hotelname);
                addressbooking1.setText(address);
                pricebooking1.setText(price);
                datesbooking1.setText(today + " - " + tomorrow + " - " + noofrooms + " - " + noofadults);

            }
        }
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle!=null){
                    String name1 = (String) name2.getText();
                    Intent intent = new Intent(Booking.this, PaymentMainActivity.class);
                    intent.putExtra("Name",name1);
                    Double check = Double.parseDouble((String) pricebooking1.getText());
                    int intParse = check.intValue();
                    intent.putExtra("Price",String.valueOf(intParse));
                    startActivity(intent);
                }}
        });

    }
}
