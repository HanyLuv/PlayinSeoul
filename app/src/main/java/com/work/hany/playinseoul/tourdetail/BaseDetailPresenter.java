package com.work.hany.playinseoul.tourdetail;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.network.AreaTour;

abstract public class BaseDetailPresenter implements BaseDetailContract.BaseDetailPresenter {
    @Nullable
    BaseDetailContract.BaseDetailView baseView;

    @Override
    public void openOverViewDetail(AreaTour tour) {
        baseView.showOverViewDetail(tour);
    }

    @Override
    public void openMapDetail(AreaTour tour) {
        baseView.showMapDetail(tour);
    }

    @Override
    public void takeView(BaseDetailContract.BaseDetailView view) {
        this.baseView = view;

    }

    @Override
    public void dropView() {
        this.baseView = null;
    }



}
