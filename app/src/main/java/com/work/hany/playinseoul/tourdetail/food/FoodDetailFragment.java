package com.work.hany.playinseoul.tourdetail.food;

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
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.adapter.FoodDetailRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * 음식점
 */
public class FoodDetailFragment extends DaggerFragment implements FoodDetailContract.View {

    @Inject
    FoodDetailContract.Presenter presenter;

    @Inject
    AreaTour areaTour;

    private FoodDetailRecyclerViewAdapter foodDetailRecyclerViewAdapter;

    @Inject
    public FoodDetailFragment() {

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
        Section overheadSection = new Section(Section.ItemType.OVERHEAD, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);
        Section mapSection = new Section(Section.ItemType.MAP, null);
        Section photosSection = new Section(Section.ItemType.PHOTOS, null);

        sections.add(imageSection);
        sections.add(overheadSection);
        sections.add(informationSection);
        sections.add(mapSection);
        sections.add(photosSection);

        foodDetailRecyclerViewAdapter = new FoodDetailRecyclerViewAdapter(sections);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView travelDetailRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        travelDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        travelDetailRecyclerView.setAdapter(foodDetailRecyclerViewAdapter);
        presenter.loadContent(areaTour.getContentId(),areaTour.getContentTypeId());

        return rootView;
    }
}
