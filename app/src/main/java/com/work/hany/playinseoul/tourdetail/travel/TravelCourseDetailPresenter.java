package com.work.hany.playinseoul.tourdetail.travel;


import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.work.hany.playinseoul.model.DataHandler;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;
import com.work.hany.playinseoul.tourdetail.BaseDetailContract;
import com.work.hany.playinseoul.tourdetail.BaseDetailPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelCourseDetailPresenter extends BaseDetailPresenter<TravelCourseDetailContract.View> implements TravelCourseDetailContract.Presenter {

    //TODO 이미지 상세 클릭, 지도 클릭 이벤트

    @Nullable
    DataHandler dataHandler;

    @Inject
    TravelCourseDetailPresenter(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    @Override
    public void loadTravelCourseDetail(int contentId, int contentTypeId) {

        dataHandler.getTourOverView(contentId, contentTypeId).enqueue(new Callback<Result<AreaTour>>() {
            @Override
            public void onResponse(Call<Result<AreaTour>> call, Response<Result<AreaTour>> response) {
                if (!call.isCanceled()) {
                    AreaTour areaTour = response.body().getResponse().getBody().getItems().getData();
                    getBaseView().initTourOverviewUi(areaTour);
                }
            }

            @Override
            public void onFailure(Call<Result<AreaTour>> call, Throwable t) {

            }
        });


        dataHandler.getTravelTour(contentId, contentTypeId).enqueue(new Callback<Result<TravelIntro>>() {
            @Override
            public void onResponse(Call<Result<TravelIntro>> call, Response<Result<TravelIntro>> response) {
                if (!call.isCanceled()) {
                    TravelIntro areaTourInformation = response.body().getResponse().getBody().getItems().getData();
                    getBaseView().initTourIntroUi(areaTourInformation);
                }
            }

            @Override
            public void onFailure(Call<Result<TravelIntro>> call, Throwable t) {
            }
        });


        // TODO ContentType 별 요청마다 반환값이 다른데 일단 고정으로하자. 유동적으로 바뀌도록 설계해야함.
        dataHandler.getTravelDetail(contentId, contentTypeId).enqueue(new Callback<Result<ArrayList<TravelDetail>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<TravelDetail>>> call, Response<Result<ArrayList<TravelDetail>>> response) {
                if (!call.isCanceled()) {
                    getBaseView().initTourDetailUi(response.body().getResponse().getBody().getItems().getData());
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<TravelDetail>>> call, Throwable t) {
            }
        });

    }

    @Override
    public void openSubTravelCourseDetail(final TravelDetail travelDetail) {
        //여행 상세 볼때
        dataHandler.getTourOverView(travelDetail.getSubContentId(), travelDetail.getContentTypeId()).enqueue(new Callback<Result<AreaTour>>() {
            @Override
            public void onResponse(Call<Result<AreaTour>> call, Response<Result<AreaTour>> response) {
                if (!call.isCanceled()) {
                    AreaTour areaTour = response.body().getResponse().getBody().getItems().getData();
                    if(travelDetail!=null && travelDetail.getSubDetailImage() !=null) {
                        areaTour.setLargeImage(travelDetail.getSubDetailImage());
                    }

                    getBaseView().showSubTravelCourseDetailUi(areaTour);
                }
            }

            @Override
            public void onFailure(Call<Result<AreaTour>> call, Throwable t) {

            }
        });
    }

}
