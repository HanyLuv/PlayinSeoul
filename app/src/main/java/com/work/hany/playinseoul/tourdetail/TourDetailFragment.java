package com.work.hany.playinseoul.tourdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.network.AreaTourInformation;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class TourDetailFragment  extends DaggerFragment implements TourDetailContract.View {

    @Inject
    TourDetailContract.Presenter presenter;

    @Inject
    AreaTourInformation areaTourInformation;

    @Inject
    public TourDetailFragment(){ }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        TextView textView = rootView.findViewById(R.id.contentIdTextView);
        textView.setText(areaTourInformation.toString());
        return rootView;
    }
}
