package com.work.hany.playinseoul.main;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.network.AreaTour;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void initTourListUi(ArrayList<AreaTour> areaTourList);
        void showTourDetailsUi(AreaTour areaTour);
        void showCategoryTourDetailsUi(ContentType type);
        void showTourListUi(int contentTypeId);
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter<View> {
        void openTourDetails(AreaTour areaTour);
        void openCategoryTourDetails(ContentType type);
        void openTourList(int contentTypeId);

        void loadTourList();

        @Override
        void takeView(MainContract.View view);

        @Override
        void dropView();
    }
}
