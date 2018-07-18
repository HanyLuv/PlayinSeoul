package com.work.hany.playinseoul.tourdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;
import com.work.hany.playinseoul.tourdetail.adapter.TourDetailRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class TourDetailFragment  extends DaggerFragment implements TourDetailContract.View {

    @Inject
    TourDetailContract.Presenter presenter;

    @Inject
    AreaTour areaTour;

    private TourDetailRecyclerViewAdapter detailRecyclerViewAdapter;

    @Inject
    public TourDetailFragment(){ }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void initDetailInformation(ArrayList<TravelDetail> travelDetails) {
        for (TravelDetail detail : travelDetails ){
            detailRecyclerViewAdapter.addSection(Section.ItemType.COURSE, detail);
        }
    }

    @Override
    public void initTourOverviewUi(AreaTour areaTour) {
        detailRecyclerViewAdapter.updateSection(Section.ItemType.INTRO, areaTour);
    }

    @Override
    public void initTourInformation(TravelInformation information) {
        detailRecyclerViewAdapter.updateSection(Section.ItemType.INFORMATION, information);
    }

    @Override
    public void initTourPhotosUi(ArrayList<TourPhoto> tourPhotos) {
//        detailRecyclerViewAdapter.updateSection(Section.ItemType.IMAGE, tourPhotos);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Section> sections = new ArrayList<>();

        Section imageSection = new Section(Section.ItemType.IMAGE, areaTour.getLargeImage());
        Section introSection = new Section(Section.ItemType.INTRO, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);
        Section photosSection = new Section(Section.ItemType.PHOTOS,new ArrayList<TourPhoto>());
        Section mapSection = new Section(Section.ItemType.MAP, null);

        sections.add(imageSection);
        sections.add(introSection);
        sections.add(informationSection);

        detailRecyclerViewAdapter = new TourDetailRecyclerViewAdapter(sections);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView detailTourRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        detailTourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        detailTourRecyclerView.setAdapter(detailRecyclerViewAdapter);

        presenter.loadTourDetail(areaTour.getContentId(), areaTour.getContentTypeId());

        return rootView;
    }
}
