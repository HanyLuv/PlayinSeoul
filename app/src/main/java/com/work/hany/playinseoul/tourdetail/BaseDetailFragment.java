package com.work.hany.playinseoul.tourdetail;

import android.os.Bundle;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.common.map.MapDetailFragment;
import com.work.hany.playinseoul.common.overview.OverViewDetailFragment;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.adapter.DetailRecyclerAdapter;
import com.work.hany.playinseoul.util.ActivityUtils;

import dagger.android.support.DaggerFragment;

import static com.work.hany.playinseoul.common.overview.OverViewDetailFragment.ARGUMENT_TOUR;

abstract public class BaseDetailFragment extends DaggerFragment implements BaseDetailContract.BaseDetailView {

    protected abstract BaseDetailContract.BaseDetailPresenter getPresenter();

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().dropView();
    }

    private DetailRecyclerAdapter.ItemListener itemListener = new DetailRecyclerAdapter.ItemListener() {
        @Override
        public void onOverViewMoreClicked(AreaTour tour) {
             getPresenter().openOverViewDetail(tour);
        }

        @Override
        public void onMapMoreClicked(AreaTour tour) {
            getPresenter().openMapDetail(tour);
        }

    };

    public DetailRecyclerAdapter.ItemListener getItemListener() {
        return itemListener;
    }

    @Override
    public void showMapDetail(AreaTour tour) {
        MapDetailFragment fragment = new MapDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_TOUR,tour);
        fragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment,R.id.fragmentLayout,true);

    }

    @Override
    public void showOverViewDetail(AreaTour tour) {
        OverViewDetailFragment fragment = new OverViewDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_TOUR,tour);
        fragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(), fragment, R.id.fragmentLayout,true);
    }
}
