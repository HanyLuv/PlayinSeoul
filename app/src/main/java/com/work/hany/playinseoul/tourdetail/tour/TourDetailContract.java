package com.work.hany.playinseoul.tourdetail.tour;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;

import java.util.ArrayList;

public interface TourDetailContract {

    interface View extends BaseView<Presenter> {
//        void initTourOverviewUi(AreaTour areaTour);
        void initTourOverviewUi(AreaTour areaTour);
        void initTourMapUi(AreaTour areaTour);
        void initTourIntroUi(TourIntro information);
        void initTourDetailUi(ArrayList<TourDetail> travelDetails);
        void initTourPhotosUi(ArrayList<TourPhoto> photos); //TODO 음... 음식때도 이거호출할텐데 이름변경생각해보자
        void showOverViewDetail(AreaTour tour);
    }

    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(TourDetailContract.View view);
        void loadContent(int contentId, int contentTypeId);
        void openOverViewDetail(AreaTour tour);
    }
}
