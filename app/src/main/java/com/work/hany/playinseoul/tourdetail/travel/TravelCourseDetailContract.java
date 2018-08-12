package com.work.hany.playinseoul.tourdetail.travel;

import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;
import com.work.hany.playinseoul.tourdetail.BaseDetailContract;

import java.util.ArrayList;

public interface TravelCourseDetailContract {

    interface View extends  BaseDetailContract.BaseDetailView {
        void initTourOverviewUi(AreaTour areaTour);
        void initTourIntroUi(TravelIntro information);
        void initTourDetailUi(ArrayList<TravelDetail> travelDetails);
        void showSubTravelCourseDetailUi(AreaTour areaTour);
    }


    interface Presenter extends  BaseDetailContract.BaseDetailPresenter {
        void loadTravelCourseDetail(int contentId, int contentTypeId);
        void openSubTravelCourseDetail(TravelDetail detail);
    }
}
