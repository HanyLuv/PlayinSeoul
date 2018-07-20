package com.work.hany.playinseoul.tourdetail.food;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;

public interface FoodDetailContract {

    interface View extends BaseView<Presenter> {
//        void initTourPhotosUi(ArrayList<TourPhoto> tourPhotos);
//        void initTourOverviewUi(AreaTour areaTour);
//        void initTourIntroUi(TravelIntro information);
//        void initDetailInformation(ArrayList<TravelDetail> travelDetails);
    }



    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(FoodDetailContract.View view);

//        void loadTravelCourseDetail(int contentId, int contentTypeId);
    }
}
