package com.minhien.musicappasm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.adapter.ViewPagerAdapter;
import com.minhien.musicappasm.fragment.LoginTabFragment;
import com.minhien.musicappasm.fragment.SignUpTabFragment;

public class LoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb, google, insta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
//        fb = findViewById(R.id.fab_fb);
//        google = findViewById(R.id.fab_google);
//        insta = findViewById(R.id.fab_instagram);
        addTab(viewPager);
        ((TabLayout)findViewById(R.id.tab_layout)).setupWithViewPager(viewPager);
    }
    public void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new LoginTabFragment(),"Login");
        adapter.add(new LoginTabFragment(),"Login");
        adapter.add(new LoginTabFragment(),"Login");
        adapter.add(new SignUpTabFragment(),"Signup");
        adapter.add(new SignUpTabFragment(),"Signup");
        adapter.add(new SignUpTabFragment(),"Signup");
        viewPager.setAdapter(adapter);
    }
}