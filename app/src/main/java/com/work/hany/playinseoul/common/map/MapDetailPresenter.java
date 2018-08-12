package com.work.hany.playinseoul.common.map;

import android.support.annotation.Nullable;

import javax.inject.Inject;

public class MapDetailPresenter implements MapDetailContract.Presenter {


    @Nullable
    MapDetailContract.View tourView;

    @Inject
    MapDetailPresenter(){ }


    @Override
    public void takeView(MapDetailContract.View view) {
        this.tourView = view;
    }

    @Override
    public void dropView() {

    }
}
