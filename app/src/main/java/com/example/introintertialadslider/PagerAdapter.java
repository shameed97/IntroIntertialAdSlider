package com.example.introintertialadslider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    private List<Album> imageList;
    private Context context;
    private LayoutInflater layoutInflater;


    public PagerAdapter(List<Album> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
        this.layoutInflater = layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Album album = imageList.get(position);
        View itemview = layoutInflater.inflate(R.layout.album_layout, container, false);
        ImageView imageView = itemview.findViewById(R.id.imageView);
        int imageId = container.getResources().getIdentifier(album.getImgageId(), "drawable", context.getPackageName());
        imageView.setImageResource(imageId);
        container.addView(itemview);
        return itemview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);
    }
}
