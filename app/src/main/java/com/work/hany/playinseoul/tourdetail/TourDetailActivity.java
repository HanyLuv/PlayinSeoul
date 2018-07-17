package com.work.hany.playinseoul.tourdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.util.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TourDetailActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_TOUR_ID = "TOUR_ID";

    @Inject
    TourDetailFragment injectedFragment; //TODO 이게 무슨차이일까?

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        //TODO 아래 구간 생각해보자
        TourDetailFragment tourDetailFragment = (TourDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentLayout);
        if (tourDetailFragment == null) {
            tourDetailFragment = injectedFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tourDetailFragment, R.id.fragmentLayout);
        }


    }
}
