package com.work.hany.playinseoul.main;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.network.AreaTourInformation;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showTourListUi(ArrayList<AreaTourInformation> areaTourInformationList);
        void showTourDetailsUi(int tourContentId);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter<View> {
        void openTourDetails(AreaTourInformation areaTourInformation);

        void loadTourList();

        @Override
        void takeView(MainContract.View view);

        @Override
        void dropView();
    }
}
