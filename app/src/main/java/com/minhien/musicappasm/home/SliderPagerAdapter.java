package com.minhien.musicappasm.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class SliderPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private ImageSliderFragment.ISliderCallBack listener;

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public void setListener(ImageSliderFragment.ISliderCallBack listener) {
        this.listener = listener;
    }

    public SliderPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList == null) {
            return 0;
        }
        return fragmentList.size();
    }

}
