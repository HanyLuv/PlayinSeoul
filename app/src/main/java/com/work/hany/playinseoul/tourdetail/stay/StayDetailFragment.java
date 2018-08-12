package com.work.hany.playinseoul.tourdetail.stay;

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
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.model.dao.StayDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.tourdetail.BaseDetailFragment;
import com.work.hany.playinseoul.tourdetail.adapter.DetailRecyclerAdapter;
import com.work.hany.playinseoul.tourdetail.adapter.TourDetailRecyclerViewAdapter;
import com.work.hany.playinseoul.util.ActivityUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.work.hany.playinseoul.common.overview.OverViewDetailFragment.ARGUMENT_TOUR;

//관광정보
public class StayDetailFragment extends BaseDetailFragment implements StayDetailContract.View {

    @Inject
    StayDetailPresenter presenter;

    @Inject
    AreaTour areaTour;

    private TourDetailRecyclerViewAdapter tourDetailRecyclerViewAdapter;


    @Inject
    public StayDetailFragment(){ }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public StayDetailPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Section> sections = new ArrayList<>();

        Section imageSection = new Section(Section.ItemType.IMAGE, areaTour.getLargeImage());
        Section overheadSection = new Section(Section.ItemType.OVERHEAD, areaTour);
        Section informationSection = new Section(Section.ItemType.INFORMATION, null);
        Section mapSection = new Section(Section.ItemType.MAP, areaTour);
//        Section photosSection = new Section(Section.ItemType.PHOTOS, new ArrayList<TourPhoto>());

        sections.add(imageSection);
        sections.add(overheadSection);
        sections.add(informationSection);
        sections.add(mapSection);
//        sections.add(photosSection);

        tourDetailRecyclerViewAdapter = new TourDetailRecyclerViewAdapter(sections,getItemListener());

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
    public void initTourDetailUi(ArrayList<StayDetail> stayDetails) {
        //TODO adapter의 staymaxcout와 여기쓰인 4는 동일하다. 코드 수정해보자..
        int maxCount = 4;
        if (stayDetails.size() < maxCount){
            maxCount = stayDetails.size();
        }
        for (int index = 0, end = stayDetails.size() ; index < maxCount ; index ++ ) {
            StayDetail currentStayDetail = stayDetails.get(index);
            if(end > maxCount) {
                currentStayDetail.setRoomOverMaxCount(true);
            }

            int maxNumber = stayDetails.size() - 1; //adapter 에서 인덱스꼬여서..
            currentStayDetail.setSerialMaxNumber(maxNumber);
            currentStayDetail.setSerialNumber(index);
            tourDetailRecyclerViewAdapter.addSection(Section.ItemType.STAY_DETAIL, currentStayDetail);
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

    @Override
    public void initTourPhotosUi(ArrayList<TourPhoto> photos) {
        if (photos.size() > 0) {
            tourDetailRecyclerViewAdapter.addSection(Section.ItemType.PHOTOS, photos);
        }
    }

    @Override
    public void showOverViewDetail(AreaTour tour) {
        OverViewDetailFragment fragment = new OverViewDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_TOUR,tour);
        fragment.setArguments(bundle);

        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,R.id.fragmentLayout,true);
    }

    @Override
    public void showMapDetail(AreaTour tour) {
        MapDetailFragment fragment = new MapDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_TOUR,tour);
        fragment.setArguments(bundle);

        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,R.id.fragmentLayout,true);
    }


}
