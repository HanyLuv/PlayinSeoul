package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;

import java.util.ArrayList;

public interface TourDetailContract {

    interface View extends BaseView<Presenter> {
        void initTourPhotosUi(ArrayList<TourPhoto> tourPhotos);
        void initTourOverviewUi(AreaTour areaTour);
        void initTourInformation(TravelInformation information);
        void initDetailInformation(ArrayList<TravelDetail> travelDetails);
    }



    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(TourDetailContract.View view);

        void loadTourDetail(int contentId, int contentTypeId);
    }
}
