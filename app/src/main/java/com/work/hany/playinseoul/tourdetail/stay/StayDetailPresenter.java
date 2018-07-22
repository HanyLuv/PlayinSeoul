package com.work.hany.playinseoul.tourdetail.stay;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.model.dao.StayDetail;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StayDetailPresenter implements StayDetailContract.Presenter {

    @Nullable
    StayDetailContract.View tourView;

    @Nullable
    DataHandler dataHandler;

    @Inject
    StayDetailPresenter(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    @Override
    public void loadContent(int contentId, int contentTypeId) {
        dataHandler.getTourOverView(contentId,contentTypeId).enqueue(new Callback<Result<AreaTour>>() {
            @Override
            public void onResponse(Call<Result<AreaTour>> call, Response<Result<AreaTour>> response) {
                if (!call.isCanceled()) {
                    AreaTour areaTour = response.body().getResponse().getBody().getItems().getData();
                    tourView.initTourOverviewUi(areaTour);
                }
            }

            @Override
            public void onFailure(Call<Result<AreaTour>> call, Throwable t) {

            }
        });

        dataHandler.<TourDetail>getStayDetail(contentId, contentTypeId).enqueue(new Callback<Result<ArrayList<StayDetail>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<StayDetail>>> call, Response<Result<ArrayList<StayDetail>>> response) {
                if (!call.isCanceled()) {
                    tourView.initTourDetailUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<StayDetail>>> call, Throwable t) {

            }
        });


        dataHandler.getPhotos(contentId, contentTypeId).enqueue(new Callback<Result<ArrayList<TourPhoto>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TourPhoto>>> call, Response<Result<ArrayList<TourPhoto>>> response) {
                if (!call.isCanceled()) {
                    tourView.initTourPhotosUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TourPhoto>>> call, Throwable t) {

            }
        });

    }


    @Override
    public void takeView(StayDetailContract.View view) {
        this.tourView = view;
    }

    @Override
    public void dropView() {

    }
}
