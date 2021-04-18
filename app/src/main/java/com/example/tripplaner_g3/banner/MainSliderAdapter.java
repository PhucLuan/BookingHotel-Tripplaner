package com.example.tripplaner_g3.banner;

import java.util.Arrays;
import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    int numPhoto;
    List<String> images;
    public  MainSliderAdapter(int numPhoto, List<String> images){
        this.numPhoto = numPhoto;
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return numPhoto;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        for (int i = position; i < numPhoto; i++) {

            viewHolder.bindImageSlide(images.get(position));
        }
    }
}

