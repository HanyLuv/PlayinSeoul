package com.work.hany.playinseoul.tourdetail.food;


import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;

import javax.inject.Inject;

public class FoodDetailPresenter implements FoodDetailContract.Presenter {

    //TODO 이미지 상세 클릭, 지도 클릭 이벤트

    @Nullable
    FoodDetailContract.View detailView;

    @Nullable
    DataHandler dataHandler;

    @Inject
    FoodDetailPresenter(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    @Override
    public void takeView(FoodDetailContract.View view) {
        this.detailView = view;

    }

    @Override
    public void dropView() {
        //TODO dropView 처리해주기이이이
    }
}
