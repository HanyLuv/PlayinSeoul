package com.work.hany.playinseoul.main;

import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.di.FragmentScoped;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.CommonModule;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourlist.TourListActivity;
import com.work.hany.playinseoul.tourlist.TourListContact;
import com.work.hany.playinseoul.tourlist.TourListFragment;
import com.work.hany.playinseoul.tourlist.TourListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.work.hany.playinseoul.tourdetail.DetailActivity.EXTRA_TOUR_ID;

@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);

}
