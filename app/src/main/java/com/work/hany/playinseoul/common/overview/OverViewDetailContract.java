package com.work.hany.playinseoul.common.overview;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.BaseView;

public interface OverViewDetailContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter<View> {
        @Override
        void takeView(OverViewDetailContract.View view);
    }
}
