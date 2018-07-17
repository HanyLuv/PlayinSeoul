package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;

public interface TourDetailContract {

    interface View extends BaseView<Presenter> {


    }



    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(View view);
    }
}
