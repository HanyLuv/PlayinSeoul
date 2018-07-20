package com.work.hany.playinseoul.main;


import android.support.annotation.Nullable;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@ActivityScoped
public class MainPresenter implements MainContract.Presenter {

    @Nullable
    MainContract.View mainView;
    @Nullable
    DataHandler dataHandler;


    @Inject
    MainPresenter(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        //TODO Main actvity에서 @Inject하고, 여기서 안하니 오류나더라. 정확히 한번 확인해보자.
    }

    @Override
    public void openTourDetails(AreaTour areaTour) {
            mainView.showTourDetailsUi(areaTour);
    }

    @Override
    public void loadTourList() {
        dataHandler.getTourList().enqueue(new Callback<Result<ArrayList<AreaTour>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<AreaTour>>> call, Response<Result<ArrayList<AreaTour>>> response) {
                if (!call.isCanceled()) {
                    ArrayList<AreaTour> areaTourInformationList = response.body().getResponse().getBody().getItems().getData();
                    mainView.initTourListUi(areaTourInformationList);
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<AreaTour>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mainView = view;


    }

    @Override
    public void dropView() {

    }
}
