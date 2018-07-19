package com.work.hany.playinseoul.tourdetail.tour;

import android.support.annotation.Nullable;

import javax.inject.Inject;

public class TourDetailPresenter implements TourDetailContract.Presenter {

    @Nullable
    TourDetailContract.View tourView;


    @Inject
    TourDetailPresenter(){
    }


    @Override
    public void takeView(TourDetailContract.View view) {
        this.tourView = view;
    }

    @Override
    public void dropView() {

    }
}
