package com.work.hany.playinseoul.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.di.ActivityScoped;
import com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourdetail.adapter.TravelCourseDetailRecyclerViewAdapter;
import com.work.hany.playinseoul.tourlist.TourListActivity;
import com.work.hany.playinseoul.tourlist.TourListFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.work.hany.playinseoul.model.ContentType.CULTURE;
//TODO : 목록 스크롤 하기이이잉ㅇ이이잉 ㅇㅁㅇ


@ActivityScoped
public class MainFragment extends DaggerFragment implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    private MainRecyclerViewAdapter mainRecyclerViewAdapter;


    private MainRecyclerViewAdapter.ItemListener itemListener = new MainRecyclerViewAdapter.ItemListener(){
        @Override
        public void onTourClicked(AreaTour tour) {
            presenter.openTourDetails(tour);
        }

        @Override
        public void onMoreTourClicked(AreaTour tour) {
//            presenter
        }

        @Override
        public void onCategoryClicked(ContentType type) {
            presenter.openCategoryTourDetails(type);
        }
    };

    @Override
    public void showCategoryTourDetailsUi(ContentType type) {
        Intent intent = new Intent(getContext(), TourListActivity.class);
        intent.putExtra( TourListFragment.ArgumentKey.CONTENT_TYPE, type.getCode());
        startActivity(intent);
    }

    @Inject
    public MainFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Section> sections = new ArrayList<>();

        ArrayList<ContentType>  areaTour = new ArrayList<>(); // TODO 데이터 만드는 클래스로 이동하자~
        areaTour.add(ContentType.FOOD);
        areaTour.add(ContentType.SHOPPING);
        areaTour.add(ContentType.TRAVEL_COURSE);
        areaTour.add(ContentType.STAY);


        Section categorySection = new Section(Section.ItemType.CATEGORY, areaTour);
        sections.add(categorySection);

        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(sections, itemListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null, false);
        RecyclerView tourRecyclerView = rootView.findViewById(R.id.tour_recycler_view);
        tourRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tourRecyclerView.setAdapter(mainRecyclerViewAdapter);
        tourRecyclerView.setNestedScrollingEnabled(false);

        presenter.loadTourList();


        return rootView;
    }

    @Override
    public void showTourDetailsUi(AreaTour areaTour) { //TODO id 만 넘길지, 아님 이미지등등 넘길지 .고민@_@
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOUR_ID, areaTour);
        startActivity(intent);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }


    @Override
    public void initTourListUi(ArrayList<AreaTour> areaTourList) {
        mainRecyclerViewAdapter.addSection(Section.ItemType.MAIN_TOUR, areaTourList);
    }


}
