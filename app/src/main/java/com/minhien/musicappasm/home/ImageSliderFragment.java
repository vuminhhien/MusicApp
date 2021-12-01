package com.minhien.musicappasm.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.minhien.musicappasm.R;

import static com.minhien.musicappasm.utils.LibUtility.sliderImage;

public class ImageSliderFragment extends Fragment {

    private FrameLayout bannerView;
    private ImageView imageComic;
    private String urlImage;
    private boolean isBanner;
    private int position;
    private FragmentActivity activity;
    private ISliderCallBack mListener;
//    private UnifiedNativeAdView nativeView;
    int resImage = 0;

    public ImageSliderFragment() {
        // Required empty public constructor
    }

    public static ImageSliderFragment newInstance(int pos, boolean type) {
        ImageSliderFragment fragment = new ImageSliderFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        args.putBoolean("isBanner", type);
        fragment.setArguments(args);
        return fragment;
    }

    public void setResImage(int res){
        this.resImage = res;
    }

    public void setCallback(ISliderCallBack listener) {
        this.mListener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.isBanner = getArguments().getBoolean("isBanner");
            this.position = getArguments().getInt("position");
        }

        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image_slider, container, false);

        bannerView = rootView.findViewById(R.id.bannerView);
//        nativeView = rootView.findViewById(R.id.tnaRoot);
        imageComic = rootView.findViewById(R.id.image);

        imageComic.setImageDrawable(ContextCompat.getDrawable(activity, resImage == 0? sliderImage[position] : resImage));

        imageComic.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onSliderCallback(position);
            }
        });

        if (activity == null) {
            activity = getActivity();
        }

//        if (isBanner) {
//            AdsBanner.getNativeView(activity, new INativeViewCallback() {
//                @Override
//                public void onLoaded(Object object) {
//                    if (object != null) {
//                        if (object instanceof AdView) {
//                            AdView adviewRectangle = (AdView) object;
//                            if (adviewRectangle.getParent() != null) {
//                                ((ViewGroup) adviewRectangle.getParent()).removeView(adviewRectangle); // <- fix
//                            }
//                            bannerView.addView(adviewRectangle);
//                            bannerView.setVisibility(View.VISIBLE);
//                            nativeView.setVisibility(View.GONE);
//
//                            adviewRectangle.loadAd(new AdRequest.Builder().build());
//                        }
//
//                        if (object instanceof UnifiedNativeAd) {
//                            UnifiedNativeAd nativeAd = (UnifiedNativeAd) object;
//                            populateUnifiedNativeAdView(nativeAd, nativeView);
//                            bannerView.setVisibility(View.GONE);
//                            nativeView.setVisibility(View.VISIBLE);
//                        }
//                    }
//                }
//
//                @Override
//                public void onError() {
//
//                }
//            });
//
//        } else {
//            nativeView.setVisibility(View.GONE);
//        }

        return rootView;
    }

//    /**
//     * Populates a {@link UnifiedNativeAdView} object with data from a given
//     * {@link UnifiedNativeAd}.
//     *
//     * @param nativeAd the object containing the ad's assets
//     * @param adView          the view to be populated
//     */
//    private static void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
//        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
//        // have a video asset.
//        VideoController vc = nativeAd.getVideoController();
//
//        // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//        // VideoController will call methods on this object when events occur in the video
//        // lifecycle.
//        vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//            public void onVideoEnd() {
//                // Publishers should allow native ads to complete video playback before refreshing
//                // or replacing them with another ad in the same UI location.
////                videoStatus.setText("Video status: Video playback has ended.");
//                super.onVideoEnd();
//            }
//        });
//
//        MediaView mediaView = adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);
//        mediaView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
//            @Override
//            public void onChildViewAdded(View parent, View child) {
//                if (child instanceof ImageView) {
//                    ImageView imageView = (ImageView) child;
//                    imageView.getLayoutParams().height = (int) parent.getContext().getResources().getDimension(R.dimen.max_height_media_view_admob);
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    imageView.requestLayout();
//                }
//            }
//
//            @Override
//            public void onChildViewRemoved(View parent, View child) {}
//        });
//
//        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
//        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
//        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
//        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//
//        // Some assets are guaranteed to be in every UnifiedNativeAd.
//        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//
//        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
//        // check before trying to display them.
//        if (nativeAd.getIcon() == null) {
//            adView.getIconView().setVisibility(View.GONE);
//        } else {
//            ((ImageView) adView.getIconView()).setImageDrawable(
//                    nativeAd.getIcon().getDrawable());
//            adView.getIconView().setVisibility(View.VISIBLE);
//        }
//
//        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
//        }
//
//        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
//        }
//
//        if (nativeAd.getStarRating() == null) {
//            adView.getStarRatingView().setVisibility(View.INVISIBLE);
//        } else {
//            ((RatingBar) adView.getStarRatingView())
//                    .setRating(nativeAd.getStarRating().floatValue());
//            adView.getStarRatingView().setVisibility(View.VISIBLE);
//        }
//
//        if (nativeAd.getAdvertiser() == null) {
//            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            adView.getAdvertiserView().setVisibility(View.VISIBLE);
//        }
//
//        adView.setNativeAd(nativeAd);
//    }

    public interface ISliderCallBack {
        void onSliderCallback(int position);
    }

}
