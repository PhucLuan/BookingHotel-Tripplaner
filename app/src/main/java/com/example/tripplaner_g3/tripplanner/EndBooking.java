package com.example.tripplaner_g3.tripplanner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplaner_g3.R;

import com.example.tripplaner_g3.Service.PicassoImageLoadingService;
import com.example.tripplaner_g3.banner.MainSliderAdapter;
import com.example.tripplaner_g3.googlemap.GoogleMapActivity;
import com.example.tripplaner_g3.weather.WeatherMainActivity;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ss.com.bannerslider.Slider;

public class EndBooking extends AppCompatActivity {
    private Slider goldentulip;
    private RadioGroup ride;
    private RadioButton radioButton;
    private Button bookbutton;
    private LinearLayout ridefare;
    private LinearLayout dob_current;
    private LinearLayout dob_later;
    private LinearLayout adult;
    private TextView ridefaretext;
    private TextView dob_current_text;
    private TextView dob_later_text;
    private TextView addresstext;
    private TextView pricetext;
    private TextView hotelname;
    private EditText name;
    private ImageButton btnWeather;
    private ImageButton btnMap;
    Double totalPrice;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endbooking);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        //FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final Calendar myCalendar = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        final String formattedDate1 = df1.format(myCalendar.getTime());
        String myFormat = "dd/MM/yy";
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        name= findViewById(R.id.namebooking);
        //final FirebaseUser currentUser = mAuth.getCurrentUser();
        ride = findViewById(R.id.ride);
        bookbutton= findViewById(R.id.bookroom);
        ridefaretext = findViewById(R.id.faretext);
        addresstext= findViewById(R.id.adresstext);
        dob_current_text = findViewById(R.id.dob_current_text);
        pricetext= findViewById(R.id.pricetext);
        hotelname= findViewById(R.id.hotelname);
        dob_later_text = findViewById(R.id.dob_later_text);
        dob_current = findViewById(R.id.dob_current);
        dob_later = findViewById(R.id.dob_later);
        final TextView noofadultss = findViewById(R.id.noofadultss);
        final TextView noofroomss = findViewById(R.id.noofroomss);
        adult = findViewById(R.id.adult);
        //goldentulip = findViewById(R.id.upperimage);
        ridefare = findViewById(R.id.bikefarelayout);
        ridefare.setVisibility(View.GONE);
        btnWeather = findViewById(R.id.image_btn_weather);
        btnMap = findViewById(R.id.image_btn_map);
        if (bundle != null) {
            final String name1=(String)bundle.get("Name");
            final String hotelname1 = (String)bundle.get("hotelname");
            String today = (String) bundle.get("Today");
            String tomorrow = (String) bundle.get("Tomorrow");
            String noofrooms = (String) bundle.get("noofrooms");
            String noofadults = (String) bundle.get("noofadults");
            final String img1 = (String) bundle.get("img1");
            final String img2 = (String) bundle.get("img2");
            final String img3 = (String) bundle.get("img3");
            final String img4 = (String) bundle.get("img4");
            String address = (String) bundle.get("address");
            final String price = (String) bundle.get("price");
            totalPrice = Double.parseDouble(price);
            hotelname.setText(hotelname1);
            dob_current_text.setText(today);
            dob_later_text.setText(tomorrow);
            noofroomss.setText(noofrooms);
            noofadultss.setText(noofadults);
            addresstext.setText(address);
            pricetext.setText(price);
            name.setText(name1);
            Slider.init(new PicassoImageLoadingService(getApplicationContext()));
            Slider slider = findViewById(R.id.banner_slider1);
            List<String> supplierNames = Arrays.asList("https://vnn-imgs-f.vgcloud.vn/2018/03/15/14/7-mon-do-noi-that-dung-phi-tien-ma-mua.jpg",
                    "https://noithatducduong.com/wp-content/uploads/2018/03/a1-5.jpg",
                    "https://timviec365.vn/pictures/news/2019/09/14/txj1568453465.jpg",
                    "https://noithattoancau.vn/uploads/.thumbs/images/thiet-ke-noi-that-chung-cu-bang-go-oc-cho-10.jpg");
            slider.setAdapter(new MainSliderAdapter(supplierNames.size(),supplierNames));

            int selectedId = ride.getCheckedRadioButtonId();
            radioButton = findViewById(selectedId);

            ride.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch(checkedId)
                    {
                        case R.id.yes:
                            ridefare.setVisibility(View.VISIBLE);
                            if((dob_current_text.getText().toString()).equals("Today"))
                            {
                                try {
                                    Date date1 = sdf.parse(formattedDate1);
                                    Date date2 = sdf.parse(dob_later_text.getText().toString());
                                    long diff = date2.getTime() - date1.getTime();
                                    int j=(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                    ridefaretext.setText(j + " X 30000");

                                    Double rideprice = Double.valueOf(j*30000);
                                    Double check = j*totalPrice*Double.parseDouble(noofrooms.substring(0,1));
//                                    totalPrice = ;
                                    pricetext.setText(""+(rideprice + check));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }else {
                                try {
                                    Date date1 = sdf.parse(dob_current_text.getText().toString());
                                    Date date2 = sdf.parse(dob_later_text.getText().toString());
                                    long diff = date2.getTime() - date1.getTime();
                                    int j=(int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                    ridefaretext.setText(j + " X 30000");
                                    Double rideprice = Double.valueOf(j*30000);
                                    pricetext.setText(""+rideprice + j*Double.parseDouble(price)*Double.parseDouble(noofrooms.substring(0,1)));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case R.id.No:
                            String check = totalPrice.toString();
                            pricetext.setText(totalPrice.toString());
                            ridefare.setVisibility(View.GONE);
                            break;

                    }
                }
            });

            bookbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(EndBooking.this, Booking.class);
                    intent1.putExtra("order","yes");
                    intent1.putExtra("Name1",name.getText().toString());
                    intent1.putExtra("hotelname",hotelname1);
                    intent1.putExtra("Today", dob_current_text.getText().toString());
                    intent1.putExtra("Tomorrow", dob_later_text.getText().toString());
                    intent1.putExtra("noofrooms", noofroomss.getText().toString());
                    intent1.putExtra("noofadults", noofadultss.getText().toString());
                    intent1.putExtra("img1", img1);
                    intent1.putExtra("img2", img2);
                    intent1.putExtra("img3", img3);
                    intent1.putExtra("img4", img4);
                    intent1.putExtra("address", addresstext.getText().toString());
                    intent1.putExtra("price", pricetext.getText().toString());
                    startActivity(intent1);
                    finish();
                }
            });
        }else{
            //mAuth.signOut();
            sendToAuth();
        }
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                if((sdf.format(myCalendar.getTime())).equals(formattedDate1)){
                    dob_current_text.setText("Today");
                }else{
                    dob_current_text.setText(sdf.format(myCalendar.getTime()));
                }
            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dob_later_text.setText(sdf.format(myCalendar.getTime()));
            }
        };
        dob_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EndBooking.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        dob_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EndBooking.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                myCalendar.add(Calendar.DATE,1);
                datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        adult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(EndBooking.this);
                dialog.setContentView(R.layout.adult_dialog);
                dialog.setCancelable(false);
                final Button verifyButton = dialog.findViewById(R.id.btn_verify);
                final ScrollableNumberPicker noofrooms= dialog.findViewById(R.id.noofrooms);
                final ScrollableNumberPicker noofadults= dialog.findViewById(R.id.noofadults);
                noofrooms.setValue(1);
                noofadults.setValue(1);
                noofrooms.setListener(new ScrollableNumberPickerListener() {
                    @Override
                    public void onNumberPicked(int value) {
                        String x = Integer.toString(value);
                        double c = Integer.parseInt(x);

                        noofroomss.setText(x + " Rooms");
                        pricetext.setText(String.valueOf(totalPrice*c));
                    }
                });
                noofadults.setListener(new ScrollableNumberPickerListener() {
                    @Override
                    public void onNumberPicked(int value) {
                        String x = Integer.toString(value);
                        noofadultss.setText(x + " Adults");
//                        double c = Integer.parseInt(x);
//                        pricetext.setText(String.valueOf(c * 1000));
                    }
                });
                verifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(), WeatherMainActivity.class);
                startActivity(intent1);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMap = new Intent(getBaseContext(), GoogleMapActivity.class);
                String check = (String) addresstext.getText();
                intentMap.putExtra("addrTo",(String) check);
                startActivity(intentMap);

            }
        });
    }
    private void sendToAuth(){

        Intent intent = new Intent(EndBooking.this,SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
