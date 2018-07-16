package com.work.hany.playinseoul.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    @Inject
    public MainFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,null,false);


        PlayInSeoulService service = PlayInSeoulRetrofit.Companion.getInstance().create(PlayInSeoulService.class);
        service.getAreaBasedList().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("HANY_TAG","안녕하세용~~ onResponse");

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("HANY_TAG","안녕하세용~~ onFailure");
            }
        });

        return rootView;
    }
}
