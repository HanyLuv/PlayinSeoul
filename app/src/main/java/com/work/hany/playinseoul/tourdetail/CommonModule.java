package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.common.map.MapDetailFragment;
import com.work.hany.playinseoul.common.overview.OverViewDetailFragment;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourlist.TourListFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerFragment;

import static com.work.hany.playinseoul.common.overview.OverViewDetailFragment.ARGUMENT_TOUR;

@Module
public abstract class CommonModule {
    @Named("OverViewDetailFragment")
    @Provides
    static AreaTour provideTourArgument(OverViewDetailFragment fragment) {
        return fragment.getArguments().getParcelable(ARGUMENT_TOUR);
    }

    @Named("MapDetailFragment")
    @Provides
    static AreaTour provideTourArgumentToMapDetailFragment(MapDetailFragment fragment) {
        return fragment.getArguments().getParcelable(ARGUMENT_TOUR);
    }

//    @Named("TourListFragment")
//    @Provides
//    static int provideContentType(TourListFragment fragment) {
//        return fragment.getArguments().getParcelable(TourListFragment.ArgumentKey.CONTENT_TYPE);
//    }

}
