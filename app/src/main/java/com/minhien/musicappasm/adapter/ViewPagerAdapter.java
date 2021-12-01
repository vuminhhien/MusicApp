package com.minhien.musicappasm.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.minhien.musicappasm.fragment.LoginTabFragment;
import com.minhien.musicappasm.fragment.SignUpTabFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> title = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
    public void add(Fragment frm, String t){
        fragments.add(frm);
        title.add(t);
    }
}
