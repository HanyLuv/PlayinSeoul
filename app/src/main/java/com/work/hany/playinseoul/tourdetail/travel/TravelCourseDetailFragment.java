package com.work.hany.playinseoul.tourdetail.travel;


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
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;
import com.work.hany.playinseoul.tourdetail.travel.adapter.TravelDetailRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class TravelCourseDetailFragment extends DaggerFragment implements TravelCourseDetailContract.View {

    @Inject
    TravelCourseDetailContract.Presenter presenter;

    @Inject
    AreaTour areaTour;

    // areaTour의 contenTypeId에 따라서 섹션을 구성해야한다.

    private TravelDetailRecyclerViewAdapter detailRecyclerViewAdapter;

    @Inject
    public TravelCourseDetailFragment(){
        Log.d("HANY_TAG","TravelCourseDetailFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Section> sections = new ArrayList<>();

        Section imageSection = new Section(Section.ItemType.IMAGE, areaTour.getLargeImage());
        Section introSection = new Section(Section.ItemType.INTRO, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);
//        Section photosSection = new Section(Section.ItemType.PHOTOS,new ArrayList<TourPhoto>());
//        Section mapSection = new Section(Section.ItemType.MAP, null);

        sections.add(imageSection);
        sections.add(introSection);
        sections.add(informationSection);

        detailRecyclerViewAdapter = new TravelDetailRecyclerViewAdapter(sections);
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

    @Override
    public void initDetailInformation(ArrayList<TravelDetail> travelDetails) {
        for (TravelDetail detail : travelDetails ){
            detailRecyclerViewAdapter.addSection(Section.ItemType.COURSE, detail);
        }
    }

    @Override
    public void initTourOverviewUi(AreaTour areaTour) { /** 해당 여행 설명*/
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
}
