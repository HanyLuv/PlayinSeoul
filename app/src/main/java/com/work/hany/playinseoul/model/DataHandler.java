package com.work.hany.playinseoul.model;

import com.work.hany.playinseoul.model.dao.StayDetail;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.network.Area;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.PlayInSeoulRetrofit;
import com.work.hany.playinseoul.network.PlayInSeoulService;
import com.work.hany.playinseoul.network.Result;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;

import java.util.ArrayList;
import java.util.HashMap;

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
    public Call<Result<ArrayList<AreaTour>>> getTourList(int numOfRows, int contentTypeId) {
        return service.getAreaBasedList(contentTypeId ,numOfRows);
    }

    @Override
    public Call<Result<ArrayList<TourPhoto>>> getPhotos(int contentId, int contentTypeId) {
        return service.getPhotos(contentId,contentTypeId);
    }

    /** 관광지에 대한 설명
     * TODO 일단.. 다른 여행들과 동일한 요청같다. 이것도 모아보장 ㅇㅅㅇ */
    @Override
    public Call<Result<AreaTour>> getTourOverView(int contentId, int contentTypeId) {
        return service.getTourOverView(contentId,contentTypeId);
    }

    /** 여행 코스 정보 */
    @Override
    public Call<Result<TravelIntro>> getTravelTour(int contentId, int contentTypeId) {
        return service.getTravelInformation(contentId,contentTypeId);
    }

    /** 반복 되는 정보 */
    @Override
    public Call<Result<ArrayList<TravelDetail>>> getTravelDetail(int contentId, int contentTypeId) {
        return service.getTravelDetailInfo(contentId,contentTypeId);
    }

    //각 반한 유형에 대해 다른 메소드를 만들어야함.. 런타임에 완전히 알려져야하기떄문 ㅠ
    //관련 주소. https://github.com/square/retrofit/issues/2012

    public Call<Result<ArrayList<TourDetail>>> getTourDetail(int contentId, int contentTypeId) {
        return service.getDetailInfo(contentId,contentTypeId);
    }


    /** 숙박에 대한 디테일 정보는 완전히 다르므로..*/
    public Call<Result<ArrayList<StayDetail>>> getStayDetail(int contentId, int contentTypeId) {
        return service.getStayDetailInfo(contentId,contentTypeId);
    }


    public Call<Result<Area>> getAreaCode(HashMap<String, String> params) {
        return service.getAreaCode(params);
    }

    public Call<Result<ArrayList<Area>>> getAreaCodeList(HashMap<String, String> params) {
        return service.getAreaCodeList(params);
    }

    public Call<Result<Area>> getCategoryCode(HashMap<String, String> params) {
        return service.getCategoryCode(params);
    }

    public Call<Result<ArrayList<Area>>> getCategoryCodeList(HashMap<String, String> params) {
        return service.getCategoryCodeList(params);
    }
}


interface DataHandlerContract {
    Call<Result<ArrayList<AreaTour>>> getTourList(int numOfRows, int contentTypeId);
    Call<Result<AreaTour>> getTourOverView(int contentId, int contentTypeId);
    Call<Result<ArrayList<TourPhoto>>> getPhotos(int contentId, int contentTypeId);
    Call<Result<TravelIntro>> getTravelTour(int contentId, int contentTypeId);
    Call<Result<ArrayList<TravelDetail>>> getTravelDetail(int contentId, int contentTypeId);
    Call<Result<ArrayList<StayDetail>>> getStayDetail(int contentId, int contentTypeId);


}