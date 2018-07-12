package com.work.hany.playinseoul.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.playinseoul.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {

    @Inject
    Lazy<MainFragment> mainFragmentProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            mainFragment = mainFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }

    }
}
