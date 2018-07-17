package com.work.hany.playinseoul.tourdetail;


import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourDetailPresenter implements TourDetailContract.Presenter {

    //TODO 이미지 상세 클릭, 지도 클릭 이벤트

    @Nullable
    TourDetailContract.View detailView;

    @Nullable
    DataHandler dataHandler;

    @Inject
    TourDetailPresenter(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    @Override
    public void loadTourDetail(int contentId, int contentTypeId) {

        dataHandler.getTourPhotos(contentId,contentTypeId).enqueue(new Callback<Result<TourPhoto>>() {
            @Override
            public void onResponse(Call<Result<TourPhoto>> call, Response<Result<TourPhoto>> response) {

                if (!call.isCanceled()) {
                    ArrayList tourPhotos = response.body().getResponse().getBody().getItems().getList();
                    detailView.initTourImageUi(tourPhotos);
                }
            }

            @Override
            public void onFailure(Call<Result<TourPhoto>> call, Throwable t) {

            }
        });

    }

    @Override
    public void takeView(TourDetailContract.View view) {
        this.detailView = view;

    }

    @Override
    public void dropView() {
        //TODO dropView 처리해주기이이이
    }
}
