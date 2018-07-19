package com.work.hany.playinseoul.tourdetail.tour;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
//관광정보
public class TourDetailFragment extends DaggerFragment implements TourDetailContract.View{

    @Inject
    TourDetailPresenter presenter;


    @Inject
    public TourDetailFragment(){
        Log.d("HANY_TAG","TourDetailFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView detailTourRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        detailTourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        detailTourRecyclerView.setAdapter(detailRecyclerViewAdapter);

        return rootView;
    }
}
