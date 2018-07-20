package com.work.hany.playinseoul.tourdetail.tour;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.adapter.TourDetailRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
//관광정보
public class TourDetailFragment extends DaggerFragment implements TourDetailContract.View{

    @Inject
    TourDetailPresenter presenter;

    @Inject
    AreaTour areaTour;

    private TourDetailRecyclerViewAdapter tourDetailRecyclerViewAdapter;

    @Inject
    public TourDetailFragment(){ }

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
        Section overheadSection = new Section(Section.ItemType.OVERHEAD, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);
        Section mapSection = new Section(Section.ItemType.MAP, areaTour);
        Section photosSection = new Section(Section.ItemType.PHOTOS, null);

        sections.add(imageSection);
        sections.add(overheadSection);
        sections.add(informationSection);
        sections.add(mapSection);
        sections.add(photosSection);

        tourDetailRecyclerViewAdapter = new TourDetailRecyclerViewAdapter(sections);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView travelDetailRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        travelDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        travelDetailRecyclerView.setAdapter(tourDetailRecyclerViewAdapter);
        presenter.loadContent(areaTour.getContentId(),areaTour.getContentTypeId());

        return rootView;
    }



    @Override
    public void initTourDetailUi(ArrayList<TourDetail> tourDetails) {
        for (TourDetail detail : tourDetails ){
            tourDetailRecyclerViewAdapter.addSection(Section.ItemType.DETAIL, detail);
        }
    }

    @Override
    public void initTourOverviewUi(AreaTour areaTour) { /** 해당 여행 설명*/
        areaTour.setMediumCategoryCode(this.areaTour.getMediumCategoryCode());
        tourDetailRecyclerViewAdapter.updateSection(Section.ItemType.OVERHEAD, areaTour);
    }

    @Override
    public void initTourIntroUi(TourIntro information) {
        tourDetailRecyclerViewAdapter.updateSection(Section.ItemType.INFORMATION, information);
    }
}
