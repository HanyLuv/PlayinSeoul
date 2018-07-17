package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.di.FragmentScoped;

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
    static int provideTourContentId(TourDetailActivity activity) {
        return activity.getIntent().getIntExtra(EXTRA_TOUR_ID,0);
    }
}
