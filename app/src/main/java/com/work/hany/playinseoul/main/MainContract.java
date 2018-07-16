package com.work.hany.playinseoul.main;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTourInformation;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showTourDetailsUi(String tourContentId);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter<View> {
        void openTourDetails(AreaTourInformation areaTourInformation);

        @Override
        void takeView(MainContract.View view);

        @Override
        void dropView();
    }
}
