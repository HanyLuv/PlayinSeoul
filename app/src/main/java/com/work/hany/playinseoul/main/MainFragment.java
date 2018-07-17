package com.work.hany.playinseoul.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter;
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.tourdetail.TourDetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//TODO : 목록 스크롤 하기이이잉ㅇ이이잉 ㅇㅁㅇ


@ActivityScoped
public class MainFragment extends DaggerFragment implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    private MainRecyclerViewAdapter mainRecyclerViewAdapter;

    public interface MainItemListener {
        void onTourClick(AreaTourInformation tourInformation);
    }

    private MainItemListener mainItemListener = new MainItemListener() {
        @Override
        public void onTourClick(AreaTourInformation tourInformation) {
            presenter.openTourDetails(tourInformation);
        }
    };

    @Inject
    public MainFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(new ArrayList<AreaTourInformation>(), mainItemListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null, false);
        RecyclerView tourRecyclerView = rootView.findViewById(R.id.tour_recycler_view);
        tourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tourRecyclerView.setAdapter(mainRecyclerViewAdapter);

        presenter.loadTourList();

        return rootView;
    }

    @Override
    public void showTourDetailsUi(int tourContentId) { //TODO id 만 넘길지, 아님 이미지등등 넘길지 .고민@_@
        Intent intent = new Intent(getContext(), TourDetailActivity.class);
        intent.putExtra(TourDetailActivity.EXTRA_TOUR_ID, tourContentId);
        startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }


    @Override
    public void showTourListUi(ArrayList<AreaTourInformation> areaTourInformationList) {
        mainRecyclerViewAdapter.setTourList(areaTourInformationList);
    }


}
