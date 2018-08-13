package com.work.hany.playinseoul.tourdetail.tour;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.tourdetail.BaseDetailContract;
import com.work.hany.playinseoul.tourdetail.BaseDetailFragment;
import com.work.hany.playinseoul.tourdetail.BaseDetailPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourDetailPresenter extends BaseDetailPresenter<TourDetailContract.View> implements TourDetailContract.Presenter {


    @Nullable
    DataHandler dataHandler;

    @Inject
    TourDetailPresenter(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    @Override
    public void loadContent(int contentId, int contentTypeId) {
        dataHandler.getTourOverView(contentId,contentTypeId).enqueue(new Callback<Result<AreaTour>>() {
            @Override
            public void onResponse(Call<Result<AreaTour>> call, Response<Result<AreaTour>> response) {
                if (!call.isCanceled()) {
                    AreaTour areaTour = response.body().getResponse().getBody().getItems().getData();
                    getBaseView().initTourOverviewUi(areaTour);
                    getBaseView().initTourMapUi(areaTour);
                }
            }

            @Override
            public void onFailure(Call<Result<AreaTour>> call, Throwable t) {

            }
        });

        dataHandler.<TourDetail>getTourDetail(contentId,contentTypeId).enqueue(new Callback<Result<ArrayList<TourDetail>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TourDetail>>> call, Response<Result<ArrayList<TourDetail>>> response) {
                if (!call.isCanceled()) {
                    getBaseView().initTourDetailUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TourDetail>>> call, Throwable t) {

            }
        });

        dataHandler.getPhotos(contentId,contentTypeId).enqueue(new Callback<Result<ArrayList<TourPhoto>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TourPhoto>>> call, Response<Result<ArrayList<TourPhoto>>> response) {
                if (!call.isCanceled()) {
                    getBaseView().initTourPhotosUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TourPhoto>>> call, Throwable t) {

            }
        });

    }



}
