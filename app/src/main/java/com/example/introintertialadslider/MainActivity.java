package com.example.introintertialadslider;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Album> imageList = new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        viewPager = findViewById(R.id.viewPager);
        prepareImage();
        pagerAdapter = new PagerAdapter(imageList, this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if ((i%4==0)){
                    if (interstitialAd.isLoaded())
                        interstitialAd.show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {

                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    private void prepareImage() {
        int count = 1;
        while (count <= 21) {
            String imageId = "pic" + Integer.toString(count);
            imageList.add(new Album(imageId));
            count++;
        }
    }
}
