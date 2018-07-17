package com.work.hany.playinseoul.tourdetail;


import android.support.annotation.Nullable;

import javax.inject.Inject;

public class TourDetailPresenter implements TourDetailContract.Presenter {

    @Nullable
    TourDetailContract.View detailView;

    @Inject
    TourDetailPresenter(){ }

    @Override
    public void takeView(TourDetailContract.View view) {
        this.detailView = view;

    }

    @Override
    public void dropView() {
        //TODO dropView 처리해주기이이이
    }
}
