package com.work.hany.playinseoul.tourdetail.travel;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;
import com.work.hany.playinseoul.tourdetail.BaseDetailPresenter;

import java.util.ArrayList;

public interface TravelCourseDetailContract {

    interface View extends BaseView<Presenter> {
        void initTourOverviewUi(AreaTour areaTour);

        void initTourIntroUi(TravelIntro information);

        void initTourDetailUi(ArrayList<TravelDetail> travelDetails);

        void showSubTravelCourseDetailUi(AreaTour areaTour);

        void showOverViewDetail(AreaTour tour);

        void showMapDetail(AreaTour tour);
    }


    interface Presenter extends BaseDetailPresenter<View> {
        @Override
        void takeView(TravelCourseDetailContract.View view);

        void loadTravelCourseDetail(int contentId, int contentTypeId);

        void openSubTravelCourseDetail(TravelDetail detail);
    }
}
