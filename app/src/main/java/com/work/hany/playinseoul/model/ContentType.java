package com.work.hany.playinseoul.model;

public enum ContentType {

   TOUR(12), //관광지
    CULTURE(14), //문화시설
    FESTIVAL(15),//행사/공연/축제
    TRAVEL_COURSE(25), //여행코스
    REPORTS(28), //레포츠
    STAY(32),//숙박
    SHOPPINGs(38),
    FOOD(39); //음식점

    private int code;
    ContentType(int code){
       this.code = code;
    }

    public int getCode() {
        return code;
    }
}
