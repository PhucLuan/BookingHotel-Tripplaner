package com.example.tripplaner_g3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripplaner_g3.Model.Hotel;
import com.example.tripplaner_g3.Model.Photo;
import com.example.tripplaner_g3.R;
import com.example.tripplaner_g3.Service.PicassoImageLoadingService;
import com.example.tripplaner_g3.banner.MainSliderAdapter;
import com.example.tripplaner_g3.tripplanner.EndBooking;
import com.example.tripplaner_g3.tripplanner.HotelsPage;

import java.util.List;
import java.util.stream.Collectors;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

public class HotelAdapter extends BaseAdapter {

    private List<Hotel> hotel_list;
    private Context context;
    public HotelAdapter(List<Hotel> hotel_list, Context context){
        this.hotel_list = hotel_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hotel_list.size();
    }

    @Override
    public Object getItem(int position) {
        return hotel_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hotel_list.get(position).getHotel_id();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            dataitem = new MyView();
            convertView = inflater.inflate(R.layout.hotel_view, null);
            dataitem.hotelName = convertView.findViewById(R.id.hotelName);
            dataitem.hotelAddress = convertView.findViewById(R.id.hotelAddress);
            dataitem.hotelPrice = convertView.findViewById(R.id.priceHotel);
            dataitem.images = convertView.findViewById(R.id.hotelSlider);
            convertView.setTag(dataitem);
        }else {
            dataitem = (MyView) convertView.getTag();
        }
        dataitem.hotelName.setText(hotel_list.get(position).getHotel_Name());
        dataitem.hotelAddress.setText(hotel_list.get(position).getHotel_Address());
        dataitem.hotelPrice.setText(""+hotel_list.get(position).getHotel_Price());
        Slider.init(new PicassoImageLoadingService(context));
        List<String> photos = hotel_list.get(position).getPhotos().stream().map(Photo::getUrl).collect(Collectors.toList());

        dataitem.images.setAdapter(new MainSliderAdapter(photos.size(),photos));
        dataitem.images.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {

                String name1 = "Luân";
                Intent intent = new Intent(context,EndBooking.class);
                intent.putExtra("Name",name1);
                intent.putExtra("hotelname", "Khách sạn Hòa Bình");
                intent.putExtra("Today", "Today");
                intent.putExtra("Tomorrow", "20/04/21");
                intent.putExtra("noofrooms", "1 Rooms");
                intent.putExtra("noofadults", "2 Adults");
                intent.putExtra("address","190, Lê Duẩn, TP Hồ Chí Minh, Việt Nam");
                intent.putExtra("price", "4000000");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class MyView{
        TextView hotelName;
        TextView hotelAddress;
        TextView hotelPrice;
        Slider images;
    }
}