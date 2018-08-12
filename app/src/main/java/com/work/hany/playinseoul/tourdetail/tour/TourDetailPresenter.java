package com.work.hany.playinseoul.tourdetail.tour;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourDetailPresenter implements TourDetailContract.Presenter {

    @Nullable
    TourDetailContract.View tourView;

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
                    tourView.initTourOverviewUi(areaTour);
                    tourView.initTourMapUi(areaTour);
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
                    tourView.initTourDetailUi(response.body().getResponse().getBody().getItems().getData());
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
                    tourView.initTourPhotosUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TourPhoto>>> call, Throwable t) {

            }
        });

    }


    @Override
    public void takeView(TourDetailContract.View view) {
        this.tourView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void openOverViewDetail(AreaTour tour) {
        //오버뷰 더 자세히 보기 할때
        tourView.showOverViewDetail(tour);
    }


    @Override
    public void openMapDetail(AreaTour tour) {
        tourView.showMapDetail(tour);

    }

}
