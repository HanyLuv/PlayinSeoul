package com.work.hany.playinseoul.tourdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.tour.TourDetailFragment;
import com.work.hany.playinseoul.tourdetail.travel.TravelCourseDetailFragment;
import com.work.hany.playinseoul.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerFragment;

public class DetailActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_TOUR_ID = "TOUR_ID";

    @Inject
    TourDetailFragment tourFragment; //TODO 이게 무슨차이일까?

    @Inject
    TravelCourseDetailFragment travelFragment; //TODO 이게 무슨차이일까?

    @Inject
    AreaTour areaTour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);

        int contentTypeId = areaTour.getContentTypeId();
        if (contentTypeId == ContentType.TOUR.getCode()){
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tourFragment, R.id.fragmentLayout);
        } else if (contentTypeId == ContentType.TRAVEL_COURSE.getCode()){
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), travelFragment, R.id.fragmentLayout);
        }
        //TODO 아래 구간 생각해보자
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentLayout);
//        if (fragment == null) {
//            tourDetailFragment = injectedFragment;
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tourDetailFragment, R.id.fragmentLayout);
//        }


    }
}
