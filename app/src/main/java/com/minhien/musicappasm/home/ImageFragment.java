package com.minhien.musicappasm.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.makeramen.roundedimageview.RoundedImageView;
import com.minhien.musicappasm.R;

public class ImageFragment extends Fragment {
    private static final String IS_CORNER = "isCorner";

    int resDrawable;
    SlideClick slideClick;

    public static ImageFragment newInstance(boolean isCorner) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_CORNER, isCorner);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        RoundedImageView imv = view.findViewById(R.id.imv);
        if(resDrawable != 0){
            imv.setImageResource(resDrawable);
        }
        if(slideClick != null){
            imv.setOnClickListener(v -> slideClick.click());
        }

        if (getArguments() != null) {
            boolean isCorner = getArguments().getBoolean(IS_CORNER, false);
            if (isCorner) {
                imv.setCornerRadius(0, 0, 70, 70);
            }
        }
        return view;
    }

    public void setSlideClick(SlideClick slideClick){
        this.slideClick = slideClick;
    }

    public void setResDrawable(int res){
        resDrawable = res;
    }

    public interface SlideClick{
        void click();
    }
}
