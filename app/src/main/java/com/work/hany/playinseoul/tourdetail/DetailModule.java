package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.common.map.MapDetailFragment;
import com.work.hany.playinseoul.common.overview.OverViewDetailFragment;
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

import static com.work.hany.playinseoul.tourdetail.DetailActivity.EXTRA_TOUR_ID;

@Module
public abstract class DetailModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract OverViewDetailFragment overViewDetailFragment();


    @FragmentScoped
    @ContributesAndroidInjector(modules = CommonModule.class)
    abstract MapDetailFragment mapDetailFragment();


    @FragmentScoped
    @ContributesAndroidInjector
    abstract TravelCourseDetailFragment travelCourseDetailFragment();

    @ActivityScoped
    @Binds
    abstract TravelCourseDetailContract.Presenter travelCourseDetailPresenter(TravelCourseDetailPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StayDetailFragment stayDetailFragment();

    @ActivityScoped
    @Binds
    abstract StayDetailContract.Presenter stayDetailPresenter(StayDetailPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TourDetailFragment tourDetailFragment();

    @ActivityScoped
    @Binds
    abstract TourDetailContract.Presenter tourDetailPresenter(TourDetailPresenter presenter);

    @Provides
    @ActivityScoped
    static AreaTour provideTourContentId(DetailActivity activity) {
        return activity.getIntent().getParcelableExtra(EXTRA_TOUR_ID);
    }

}
