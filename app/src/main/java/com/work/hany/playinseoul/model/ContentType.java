package com.work.hany.playinseoul.model;

public enum ContentType {

   TOUR(12, "관광지"), //관광지
    CULTURE(14, "문화시설"), //문화시설
    FESTIVAL(15, "행사/공연/축제"),//행사/공연/축제
    TRAVEL_COURSE(25, "여행코스"), //여행코스
    REPORTS(28,"레포츠"), //레포츠
    STAY(32,"숙박"),//숙박
    SHOPPING(38,"쇼핑"), //쇼핑
    FOOD(39,"음식점"), //음식점
    EMPTY(40,""); //빈값

    private int code;
    private String tagName;
    ContentType(int code, String tagName){
       this.code = code;
       this.tagName = tagName;
    }

    public int getCode() {
        return code;
    }

 public String getTagName() {
  return tagName;
 }
}
