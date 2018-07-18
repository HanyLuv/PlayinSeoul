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

    public String getSmallCategoryToWord(String smallCategoryCode){
        String word = "";
        switch (smallCategoryCode) {
            case "C01120001":
                word = "가족코스";
                break;

            case "C01130001":
                word = "나홀로코스";
                break;

            case "C01140001":
                word = "힐링코스";
                break;

            case "C01150001":
                word = "도보코스";

                break;
            case "C01160001":
                word = "캠핑코스";

                break;
            case "C01170001":
                word = "맛코스";

                break;

        }
        return word;
    }
}


interface DataHandlerContract {
    Call<Result<ArrayList<AreaTour>>> getTourList();
    Call<Result<AreaTour>> getTourOverView(int contentId, int contentTypeId);
    Call<Result<ArrayList<TourPhoto>>> getTourPhotos(int contentId, int contentTypeId);
    Call<Result<TravelInformation>> getTravelTourInformation(int contentId, int contentTypeId);
    Call<Result<ArrayList<TravelDetail>>> getTravelDetailInformation(int contentId, int contentTypeId);


}