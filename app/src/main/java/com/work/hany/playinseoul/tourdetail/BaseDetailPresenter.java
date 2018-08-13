package com.work.hany.playinseoul.tourdetail;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.BaseDetailContract.BaseDetailView;

abstract public class BaseDetailPresenter<T extends BaseDetailContract.BaseDetailView> implements BaseDetailContract.BaseDetailPresenter {

    @Nullable
    T baseView;

    @Override
    public void openOverViewDetail(AreaTour tour) {
        baseView.showOverViewDetail(tour);
    }

    @Override
    public void openMapDetail(AreaTour tour) {
        baseView.showMapDetail(tour);
    }

    public void takeView(BaseDetailView view) {
        baseView = (T) view;
    }

    @Override
    public void dropView() {
        this.baseView = null;
    }

    @Nullable
    public T getBaseView() {
        return baseView;
    }
}
