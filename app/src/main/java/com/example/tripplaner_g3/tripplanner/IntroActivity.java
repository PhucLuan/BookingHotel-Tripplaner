package com.example.tripplaner_g3.tripplanner;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import com.example.tripplaner_g3.R;


import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;
import io.github.dreierf.materialintroscreen.animations.IViewTranslation;

//public class IntroActivity extends MaterialIntroActivity {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        addSlide(new SlideFragmentBuilder()
//                        .backgroundColor(R.color.colorPrimary)
//                        .buttonsColor(R.color.colorAccent)
//                        .possiblePermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS})
//                        .neededPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
//                        .image(io.github.dreierf.materialintroscreen.R.drawable.ic_next)
//                        .title("title 3")
//                        .description("Description 3")
//                        .build(),
//                new MessageButtonBehaviour(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showMessage("We provide solutions to make you love your work");
//                    }
//                }, "Work with love"));
//    }
//}


public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.first_slide_background)
                        .buttonsColor(R.color.first_slide_buttons)
                        .image(R.drawable.earth)
                        .title("All Hotels")
                        .description("around the globe")
                        .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.second_slide_background)
                .buttonsColor(R.color.second_slide_buttons)
                .image(R.drawable.wifi)
                .title("Free Wifi")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.breakfast)
                .title("AC Rooms with breakfast free")
                .description("Clean and Hygienic Rooms")
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.fourth_slide_background)
                .buttonsColor(R.color.fourth_slide_buttons)
                .image(R.drawable.cash)
                .title("Book Now")
                .description("pay later !!! ")
                .build());



    }


}