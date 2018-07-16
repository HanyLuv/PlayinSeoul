package com.work.hany.playinseoul.main;



import android.support.annotation.Nullable;
import android.util.Log;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.network.AreaTourInformation;

import javax.inject.Inject;


@ActivityScoped
public class MainPresenter implements MainContract.Presenter {

    @Nullable
    MainContract.View mainView;

    @Inject
    MainPresenter(){
        //TODO Main actvity에서 @Inject하고, 여기서 안하니 오류나더라. 정확히 한번 확인해보자.
        Log.d("HANY_TAG","만들어졌어요.");
    }

    @Override
    public void openTourDetails(AreaTourInformation areaTourInformation) {

    }

    @Override
    public void takeView(MainContract.View view) {
        this.mainView = view;


    }

    @Override
    public void dropView() {

    }
}
