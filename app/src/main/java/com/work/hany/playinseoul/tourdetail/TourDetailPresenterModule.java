package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.di.FragmentScoped;
import com.work.hany.playinseoul.network.AreaTourInformation;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.work.hany.playinseoul.tourdetail.TourDetailActivity.EXTRA_TOUR_ID;

@Module
public abstract class TourDetailPresenterModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TourDetailFragment tourDetailFragment();


    @ActivityScoped
    @Binds
    abstract TourDetailContract.Presenter tourDetailPresenter(TourDetailPresenter presenter);

    @Provides
    @ActivityScoped
    static AreaTourInformation provideTourContentId(TourDetailActivity activity) {
        return activity.getIntent().getParcelableExtra(EXTRA_TOUR_ID);
    }
}
