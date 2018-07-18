package com.work.hany.playinseoul.tourdetail;


import android.support.annotation.Nullable;
import android.util.Log;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;

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

        dataHandler.getTourPhotos(contentId,contentTypeId).enqueue(new Callback<Result<ArrayList<TourPhoto>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TourPhoto>>> call, Response<Result<ArrayList<TourPhoto>>> response) {

                if (!call.isCanceled()) {
                    ArrayList tourPhotos = response.body().getResponse().getBody().getItems().getData();
                    detailView.initTourPhotosUi(tourPhotos);
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TourPhoto>>> call, Throwable t) {

            }
        });

        dataHandler.getTourOverView(contentId, contentTypeId).enqueue(new Callback<Result<AreaTour>>() {
            @Override
            public void onResponse(Call<Result<AreaTour>> call, Response<Result<AreaTour>> response) {
                if (!call.isCanceled()) {
                    AreaTour areaTour = response.body().getResponse().getBody().getItems().getData();
                    detailView.initTourOverviewUi(areaTour);
                }
            }

            @Override
            public void onFailure(Call<Result<AreaTour>> call, Throwable t) {

            }
        });


        dataHandler.getTravelTourInformation(contentId, contentTypeId).enqueue(new Callback<Result<TravelInformation>>() {
            @Override
            public void onResponse(Call<Result<TravelInformation>> call, Response<Result<TravelInformation>> response) {
                if (!call.isCanceled()) {
                    TravelInformation areaTourInformation = response.body().getResponse().getBody().getItems().getData();
                    detailView.initTourInformation(areaTourInformation);
                }
            }

            @Override
            public void onFailure(Call<Result<TravelInformation>> call, Throwable t) {
                Log.d("HANY_TAG","hi");

            }
        });


        // TODO ContentType 별 요청마다 반환값이 다른데 일단 고정으로하자. 유동적으로 바뀌도록 설계해야함.
        dataHandler.getTravelDetailInformation(contentId, contentTypeId).enqueue(new Callback<Result<ArrayList<TravelDetail>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TravelDetail>>> call, Response<Result<ArrayList<TravelDetail>>> response) {
                if (!call.isCanceled()) {
                    detailView.initDetailInformation(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TravelDetail>>> call, Throwable t) {
                Log.d("HANY_TAG","hi");
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
