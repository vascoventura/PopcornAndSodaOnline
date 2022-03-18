package com.example.popcornandsodaonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SliderAdapter_Details_Productions extends PagerAdapter {

    private Context context;
    private List<String> mList;

    public SliderAdapter_Details_Productions(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.item_productions_slider, null);

        ImageView slideImage = slideLayout.findViewById(R.id.slider_img_production);

        try{
            URL imageurl = new URL(ConnectionDb.CONECTIONIP + mList.get(position));
            System.out.println("BACKGROUND PRODUCAO: " + imageurl);
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
