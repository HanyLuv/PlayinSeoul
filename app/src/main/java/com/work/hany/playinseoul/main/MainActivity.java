package com.work.hany.playinseoul.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
//    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Lazy<MainFragment> mainFragmentProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            mainFragment = mainFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }


        // TODO 화면 다시 그릴때 부분 처리 생각.

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        outState.putSerializable(CURRENT_FILTERING_KEY, mainPresenter.getf);
        super.onSaveInstanceState(outState);
    }
}
