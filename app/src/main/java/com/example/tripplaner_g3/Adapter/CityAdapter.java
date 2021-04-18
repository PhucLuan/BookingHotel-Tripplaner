package com.example.tripplaner_g3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripplaner_g3.Model.City;
import com.example.tripplaner_g3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    private List<City> city_list;
    private Context context;
    public CityAdapter(List<City> photo_list, Context context){
        this.city_list = photo_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return city_list.size();
    }

    @Override
    public Object getItem(int position) {
        return city_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return city_list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            dataitem = new MyView();
            convertView = inflater.inflate(R.layout.article_disp_tpl, null);
            dataitem.iv_photo = convertView.findViewById(R.id.imv_photo);
            dataitem.tv_caption = convertView.findViewById(R.id.tv_title);
            convertView.setTag(dataitem);
        }else {
            dataitem = (MyView) convertView.getTag();
        }
        Picasso.with(context).load(city_list.get(position).getUrl()).resize(300, 400).centerCrop().into(dataitem.iv_photo);
        dataitem.tv_caption.setText(city_list.get(position).getName());
        return convertView;
    }

    private static class MyView{
        ImageView iv_photo;
        TextView tv_caption;
    }
}
