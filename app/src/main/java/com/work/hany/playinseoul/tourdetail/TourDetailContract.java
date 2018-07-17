package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

public interface TourDetailContract {

    interface View extends BaseView<Presenter> {
        void initTourImageUi(ArrayList<TourPhoto> tourPhotos);

    }



    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(TourDetailContract.View view);

        void loadTourDetail(int contentId, int contentTypeId);
    }
}
