package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.BasePresenter;
import com.work.hany.playinseoul.network.AreaTour;

public interface BaseDetailPresenter<T> extends BasePresenter<T> {
    void openOverViewDetail(AreaTour tour);
    void openMapDetail(AreaTour tour);
}
