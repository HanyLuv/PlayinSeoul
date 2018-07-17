package com.work.hany.playinseoul.model;

import android.util.Log;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class DataHandler implements DataHandlerContract {
    private PlayInSeoulService service;

    @Inject
    public DataHandler() {
        service = PlayInSeoulRetrofit.Companion.getInstance().create(PlayInSeoulService.class);
    }

    @Override
    public Call<Result<AreaTourInformation>> getTourList() {
        return service.getAreaBasedList();
    }

    @Override
    public Call<Result<TourPhoto>> getTourPhotos(int contentId, int contentTypeId) {
        return service.getTourPhotos(contentId,contentTypeId);
    }

}


interface DataHandlerContract {
    Call<Result<AreaTourInformation>> getTourList();
    Call<Result<TourPhoto>> getTourPhotos(int contentId, int contentTypeId);


}