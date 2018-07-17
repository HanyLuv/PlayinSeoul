package com.work.hany.playinseoul.tourdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.tourdetail.adapter.TourDetailRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class TourDetailFragment  extends DaggerFragment implements TourDetailContract.View {

    @Inject
    TourDetailContract.Presenter presenter;

    @Inject
    AreaTourInformation areaTourInformation;

    private TourDetailRecyclerViewAdapter detailRecyclerViewAdapter;

    @Inject
    public TourDetailFragment(){ }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void initTourImageUi(ArrayList<TourPhoto> tourPhotos) {
//        detailRecyclerViewAdapter.updateSection(Section.ItemType.IMAGE, tourPhotos);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Section> sections = new ArrayList<>();

        Section imageSection = new Section(Section.ItemType.IMAGE, areaTourInformation.getLargeImage());
        Section informationSection = new Section(Section.ItemType.INFORMATION, areaTourInformation);
        Section introSection = new Section(Section.ItemType.INTRO,null);
        Section photosSection = new Section(Section.ItemType.PHOTOS,new ArrayList<TourPhoto>());
        Section mapSection = new Section(Section.ItemType.MAP, null);

        sections.add(imageSection);
        sections.add(informationSection);
        sections.add(introSection);
        sections.add(photosSection);
        sections.add(mapSection);

        detailRecyclerViewAdapter = new TourDetailRecyclerViewAdapter(sections);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView detailTourRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        detailTourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        detailTourRecyclerView.setAdapter(detailRecyclerViewAdapter);

//        presenter.loadTourDetail(areaTourInformation.getContentId(), areaTourInformation.getContentTypeId());

        return rootView;
    }
}
