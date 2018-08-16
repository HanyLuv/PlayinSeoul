package com.work.hany.playinseoul.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.tourlist.TourListActivity;
import com.work.hany.playinseoul.tourlist.TourListFragment;
import com.work.hany.playinseoul.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
//    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";

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


        findViewById(R.id.search_icon_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TourListActivity.class);
                startActivity(intent);
            }
        });
    }

}
