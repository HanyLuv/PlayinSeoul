package com.work.hany.playinseoul.common;

import android.support.annotation.Nullable;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.tourdetail.tour.TourDetailContract;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
