package com.example.popcornandsodaonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SliderAdapter_Details_Movies extends PagerAdapter {

    private Context context;
    private List<String> mList = new ArrayList<>();

    public SliderAdapter_Details_Movies(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item_movies_slider, null);

        ImageView slideImage = slideLayout.findViewById(R.id.slider_img);

        try{
            URL imageurl = new URL(mList.get(position));
            Glide.with(context).load(imageurl).into(slideImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
