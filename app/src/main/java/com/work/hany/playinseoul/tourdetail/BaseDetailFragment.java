package com.work.hany.playinseoul.tourdetail;

import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.adapter.DetailRecyclerAdapter;

import dagger.android.support.DaggerFragment;

abstract public class BaseDetailFragment extends DaggerFragment{

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

    protected abstract BaseDetailPresenter getPresenter();

}
