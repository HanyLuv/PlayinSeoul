package com.work.hany.playinseoul.tourdetail.travel;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.common.map.MapDetailFragment;
import com.work.hany.playinseoul.common.overview.OverViewDetailFragment;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;
import com.work.hany.playinseoul.tourdetail.BaseDetailContract;
import com.work.hany.playinseoul.tourdetail.BaseDetailFragment;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourdetail.adapter.TravelCourseDetailRecyclerViewAdapter;
import com.work.hany.playinseoul.util.ActivityUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.work.hany.playinseoul.common.overview.OverViewDetailFragment.ARGUMENT_TOUR;

@ActivityScoped
public class TravelCourseDetailFragment extends BaseDetailFragment implements TravelCourseDetailContract.View {

    @Inject
    TravelCourseDetailContract.Presenter presenter;

    @Inject
    AreaTour areaTour;

    private TravelCourseDetailRecyclerViewAdapter detailRecyclerViewAdapter;

    private TravelCourseDetailRecyclerViewAdapter.TravelCourseItemListener travelCourseItemListener = new TravelCourseDetailRecyclerViewAdapter.TravelCourseItemListener(){
        @Override
        public void onSubCourseDetailShowClicked(TravelDetail travelDetail) {
            presenter.openSubTravelCourseDetail(travelDetail);
        }
    };

    @Inject
    public TravelCourseDetailFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Section> sections = new ArrayList<>();

        Section imageSection = new Section(Section.ItemType.IMAGE, areaTour.getLargeImage());
        Section overheadSection = new Section(Section.ItemType.OVERHEAD, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);

        sections.add(imageSection);
        sections.add(overheadSection);
        sections.add(informationSection);

        detailRecyclerViewAdapter = new TravelCourseDetailRecyclerViewAdapter(sections, getItemListener());
        detailRecyclerViewAdapter.setTravelCourseItemListener(travelCourseItemListener);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,null,false);

        RecyclerView detailTourRecyclerView = rootView.findViewById(R.id.tour_detail_recycler_view);
        detailTourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        detailTourRecyclerView.setAdapter(detailRecyclerViewAdapter);

        presenter.loadTravelCourseDetail(areaTour.getContentId(), areaTour.getContentTypeId());

        return rootView;
    }

    @Override
    public void initTourDetailUi(ArrayList<TravelDetail> travelDetails) {
        for (TravelDetail detail : travelDetails ){
            detailRecyclerViewAdapter.addSection(Section.ItemType.DETAIL, detail);
        }
    }

    @Override
    public void showSubTravelCourseDetailUi(AreaTour areaTour) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOUR_ID, areaTour);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void initTourOverviewUi(AreaTour areaTour) { /** 해당 여행 설명*/
        areaTour.setMediumCategoryCode(this.areaTour.getMediumCategoryCode());
        detailRecyclerViewAdapter.updateSection(Section.ItemType.OVERHEAD, areaTour);
    }

    @Override
    public void initTourIntroUi(TravelIntro information) {
        detailRecyclerViewAdapter.updateSection(Section.ItemType.INFORMATION, information);
    }


    @Override
    protected BaseDetailContract.BaseDetailPresenter getPresenter() {
        return presenter;
    }

}
