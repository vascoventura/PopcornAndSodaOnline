package com.example.popcornandsodaonline.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Categories_Details_Adapter extends PagerAdapter {
    Context c;
    List<String> list = new ArrayList<>();

    public Categories_Details_Adapter(Context c, List<String> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
