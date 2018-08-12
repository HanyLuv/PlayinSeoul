package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTour;

public interface BaseDetailContract {

    interface BaseDetailView extends BaseView<BaseDetailPresenter> {
        void showOverViewDetail(AreaTour tour);
        void showMapDetail(AreaTour tour);
    }

    interface BaseDetailPresenter extends BasePresenter<BaseDetailView> {
        @Override
        void takeView(BaseDetailView view);
        void openOverViewDetail(AreaTour tour);
        void openMapDetail(AreaTour tour);
    }

}