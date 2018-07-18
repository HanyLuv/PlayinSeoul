package com.work.hany.playinseoul.model;

import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;


@Singleton
public class DataHandler implements DataHandlerContract {
    private PlayInSeoulService service;

    @Inject
    public DataHandler() {
        service = PlayInSeoulRetrofit.Companion.getInstance().create(PlayInSeoulService.class);
    }

    @Override
    public   Call<Result<ArrayList<AreaTour>>> getTourList() {
        return service.getAreaBasedList();
    }

    @Override
    public Call<Result<ArrayList<TourPhoto>>> getTourPhotos(int contentId, int contentTypeId) {
        return service.getTourPhotos(contentId,contentTypeId);
    }

    /** 관광지에 대한 설명 */
    @Override
    public Call<Result<AreaTour>> getTourOverView(int contentId, int contentTypeId) {
        return service.getTourOverView(contentId,contentTypeId);
    }

    /** 여행 코스 정보 */
    @Override
    public Call<Result<TravelInformation>> getTravelTourInformation(int contentId, int contentTypeId) {
        return service.getTravelTourInformation(contentId,contentTypeId);
    }

    /** 반복 되는 정보 */
    @Override
    public Call<Result<ArrayList<TravelDetail>>> getTravelDetailInformation(int contentId, int contentTypeId) {
        return service.getTravelDetailInfo(contentId,contentTypeId);
    }
}


interface DataHandlerContract {
    Call<Result<ArrayList<AreaTour>>> getTourList();
    Call<Result<AreaTour>> getTourOverView(int contentId, int contentTypeId);
    Call<Result<ArrayList<TourPhoto>>> getTourPhotos(int contentId, int contentTypeId);
    Call<Result<TravelInformation>> getTravelTourInformation(int contentId, int contentTypeId);
    Call<Result<ArrayList<TravelDetail>>> getTravelDetailInformation(int contentId, int contentTypeId);


}