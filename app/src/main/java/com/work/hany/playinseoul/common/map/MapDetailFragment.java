package com.work.hany.playinseoul.common.map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.network.AreaTour;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class MapDetailFragment extends DaggerFragment implements MapDetailContract.View {
    public static final String ARGUMENT_TOUR = "ARGUMENT_TOUR";
    public static final String BUNDLE_TOUR = "BUNDLE_TOUR";

    @Inject
    @Named("MapDetailFragment")
    AreaTour areaTour;

    @Inject
    public MapDetailFragment(){ }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
//        presenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, null, false);

        return rootView;
    }
}
