package com.work.hany.playinseoul.common.map;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;

public interface MapDetailContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(MapDetailContract.View view);
    }
}
