package com.example.tripplaner_g3.tripplanner;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplaner_g3.R;
import com.example.tripplaner_g3.ViewData.CityViewData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class HotelsPage extends AppCompatActivity {
    private LinearLayout whatsapp;
    private LinearLayout searchlocality;
    private LinearLayout ad1;
    private LinearLayout ad2;
    private LinearLayout ad3;
    private LinearLayout ad4;
    private LinearLayout ad5;
    private LinearLayout ad6;
    private LinearLayout ad7;
    private LinearLayout ad8;
    private LinearLayout ad9;
    private ImageButton profile;
    private ImageButton wallet;
    private TextView name;
    private Button search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        setContentView(R.layout.hotels_page);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow1 = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        final String tomorrowAsString = dateFormat.format(tomorrow);
        final String tomorrowAsString1 = dateFormat.format(tomorrow1);
        CircleImageView iv = findViewById(R.id.nearby);
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                iv,PropertyValuesHolder.ofFloat("scaleX", 0.96f),
                PropertyValuesHolder.ofFloat("scaleY", 0.96f));
        scaleDown.setDuration(300);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();
        ad1= findViewById(R.id.ad1);
        ad2= findViewById(R.id.ad2);
        ad3= findViewById(R.id.ad3);
        ad4= findViewById(R.id.ad4);
        ad5= findViewById(R.id.ad5);
        ad6= findViewById(R.id.ad6);
        ad7= findViewById(R.id.ad7);
        ad8= findViewById(R.id.ad8);
        ad9= findViewById(R.id.ad9);
        profile= findViewById(R.id.profile);
        search= findViewById(R.id.searchbox);
        wallet = findViewById(R.id.wallet);
        searchlocality= findViewById(R.id.searchlocality);
        name = findViewById(R.id.name);
        whatsapp= findViewById(R.id.werwhatsapp);

        if(bundle!=null) {
            String name1 = (String) bundle.get("Name");
            name.setText("Hello, "+ name1);
        }
        //CallAPI
        final GridView gridView = findViewById(R.id.Test);
        new CityViewData(getBaseContext(),gridView).execute();
        gridView.setOnItemClickListener(onitemclick);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle!=null){
                    String name1 = (String)bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, ProfileActivity.class);
                    intent.putExtra("Name", name1);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(HotelsPage.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null){
                    String name1 = (String)bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this,Booking.class);
                    intent.putExtra("Name", name1);
                    intent.putExtra("order","yes");
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(HotelsPage.this,Booking.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(whatsappInstalledOrNot()){
                    savecontact();
                    Uri uri = Uri.parse("smsto:" + " ");
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                }else {
                    Uri uri = Uri.parse("market://details?id=com.whatsapp");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goToMarket);
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle!=null){
                    String name1 = (String)bundle.get("Name");
                Intent intent = new Intent(HotelsPage.this, SearchLocalities.class);
                intent.putExtra("Name",name1);
                startActivity(intent);
            }}
        });
        searchlocality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle!=null){
                    String name1 = (String)bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, SearchLocalities.class);
                    intent.putExtra("Name",name1);
                    startActivity(intent);
                }}
        });

        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Renaissance Lucknow Hotel");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "2 Adults");
                    intent.putExtra("address","190, Lê Duẩn, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "4000000");
                    Toast.makeText(HotelsPage.this, "50% discount", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Hotel Kamal");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Võ Thị Sáu, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "2000000");
                    Toast.makeText(HotelsPage.this, "83% discount", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Novotel Goa Resort and Spa");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","Võ Văn Kiệt");
                    intent.putExtra("price", "2500000");
                    Toast.makeText(HotelsPage.this, "90% discount", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Novotel Goa Resort and Spa");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Phạm Hùng, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "3000000");
                    Toast.makeText(HotelsPage.this, "you have unlocked premium rooms with 55% discount", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Renaissance Lucknow Hotel");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString1);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "2 Adults");
                    intent.putExtra("address","190, Nguyễn Tri Phương, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "1200000");
                    Toast.makeText(HotelsPage.this, "50% discount for 3 days 2 night", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Park Hyatt");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Ngô Quyền, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "1400000");
                    Toast.makeText(HotelsPage.this, "55% discount", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "Bengaluru Marriott");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Lê Văn Sĩ, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "15000000");
                    startActivity(intent);
                }
            }

        });
        ad8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "JW Marriott");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Nguyễn Văn Linh, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "1499000");
                    Toast.makeText(HotelsPage.this, "pay ₹1499", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });
        ad9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(bundle!=null) {
                    String name1 = (String) bundle.get("Name");
                    Intent intent = new Intent(HotelsPage.this, EndBooking.class);
                    intent.putExtra("Name",name1);
                    intent.putExtra("hotelname", "The Oberoi");
                    intent.putExtra("Today", "Today");
                    intent.putExtra("Tomorrow", tomorrowAsString1);
                    intent.putExtra("noofrooms", "1 Rooms");
                    intent.putExtra("noofadults", "1 Adults");
                    intent.putExtra("address","190, Nguyễn Chí Thanh, TP Hồ Chí Minh, Việt Nam");
                    intent.putExtra("price", "999000");
                    Toast.makeText(HotelsPage.this, "pay now ₹999", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

        });

    }
    AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getBaseContext(), ViewArticleActivity.class);
            //intent.putExtra("id", gridView.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };
    private boolean whatsappInstalledOrNot() {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
    private void savecontact(){

        String DisplayName = "Trip Planner";
        String MobileNumber = " ";

        ArrayList<ContentProviderOperation> ops =
                new ArrayList<>();

        int rawContactID = ops.size();

        // Adding insert operation to operations list
        // to insert a new raw contact in the table ContactsContract.RawContacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // Adding insert operation to operations list
        // to insert display name in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, DisplayName)
                .build());

        // Adding insert operation to operations list
        // to insert Mobile Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try{
            // Executing all the insert operations as a single database transaction
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
             }catch (RemoteException | OperationApplicationException e) {
            e.printStackTrace();
        }
    }

   }
