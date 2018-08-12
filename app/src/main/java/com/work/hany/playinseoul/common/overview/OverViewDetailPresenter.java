package com.work.hany.playinseoul.common.overview;

import android.support.annotation.Nullable;

import javax.inject.Inject;

public class OverViewDetailPresenter implements OverViewDetailContract.Presenter {


    @Nullable
    OverViewDetailContract.View tourView;

    @Inject
    OverViewDetailPresenter(){ }


    @Override
    public void takeView(OverViewDetailContract.View view) {
        this.tourView = view;
    }

    @Override
    public void dropView() {

    }
}
