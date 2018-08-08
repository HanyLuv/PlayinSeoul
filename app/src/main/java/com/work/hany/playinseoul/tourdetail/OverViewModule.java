package com.work.hany.playinseoul.tourdetail;

import android.support.v4.app.Fragment;

import com.work.hany.playinseoul.common.OverViewDetailFragment;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.di.FragmentScoped;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.stay.StayDetailContract;
import com.work.hany.playinseoul.tourdetail.stay.StayDetailFragment;
import com.work.hany.playinseoul.tourdetail.stay.StayDetailPresenter;
import com.work.hany.playinseoul.tourdetail.tour.TourDetailContract;
import com.work.hany.playinseoul.tourdetail.tour.TourDetailFragment;
import com.work.hany.playinseoul.tourdetail.tour.TourDetailPresenter;
import com.work.hany.playinseoul.tourdetail.travel.TravelCourseDetailContract;
import com.work.hany.playinseoul.tourdetail.travel.TravelCourseDetailFragment;
import com.work.hany.playinseoul.tourdetail.travel.TravelCourseDetailPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.work.hany.playinseoul.common.OverViewDetailFragment.ARGUMENT_TOUR;
import static com.work.hany.playinseoul.tourdetail.DetailActivity.EXTRA_TOUR_ID;

@Module
public abstract class OverViewModule {
    @FragmentScoped
    @Provides
    static AreaTour provideTourArgument(Fragment fragment) {
        return fragment.getArguments().getParcelable(ARGUMENT_TOUR);
    }
}
