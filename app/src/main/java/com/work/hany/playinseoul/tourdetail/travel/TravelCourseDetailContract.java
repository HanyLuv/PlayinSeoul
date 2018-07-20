package com.work.hany.playinseoul.tourdetail.travel;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;

import java.util.ArrayList;

public interface TravelCourseDetailContract {

    interface View extends BaseView<Presenter> {
        void initTourOverviewUi(AreaTour areaTour);
        void initTourIntroUi(TravelIntro information);
        void initTourDetailUi(ArrayList<TravelDetail> travelDetails);
    }


    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(TravelCourseDetailContract.View view);
        void loadTravelCourseDetail(int contentId, int contentTypeId);
    }
}
