package com.minhien.musicappasm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.minhien.musicappasm.R;
import com.minhien.musicappasm.home.ImageFragment;
import com.minhien.musicappasm.home.SliderPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    CardView cv_music, cv_category, cv_album, _cv_user;
    ViewPager viewPager;
    DotsIndicator dotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cv_music = findViewById(R.id.cv_music);
        cv_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        buildSlide();
    }
    private void buildSlide() {
        viewPager = findViewById(R.id.viewpager);
        dotsIndicator = findViewById(R.id.dots_indicator);

        List<Fragment> fragments = new ArrayList<>();

        ImageFragment i1 = new ImageFragment();
//        i1.setSlideClick(this);
        i1.setResDrawable(R.drawable.bai_badliar);
        fragments.add(i1);

        ImageFragment i2 = new ImageFragment();
//        i1.setSlideClick(this);
        i2.setResDrawable(R.drawable.bai_3107_2);
        fragments.add(i2);

        ImageFragment i3 = new ImageFragment();
//        i1.setSlideClick(this);
        i3.setResDrawable(R.drawable.bai_anh_biet);
        fragments.add(i3);

        SliderPagerAdapter adapter = new SliderPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentList(fragments);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }
}