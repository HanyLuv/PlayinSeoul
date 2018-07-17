package com.work.hany.playinseoul.model;

import android.util.Log;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class DataHandler implements DataHandlerContract {

    @Inject
    public DataHandler(){
    }

    @Override
    public Call<Result> getTourList() {
        PlayInSeoulService service = PlayInSeoulRetrofit.Companion.getInstance().create(PlayInSeoulService.class);
        return  service.getAreaBasedList();
    }
}



interface DataHandlerContract {
    Call<Result> getTourList();


}