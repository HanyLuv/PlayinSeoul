package com.work.hany.playinseoul.model.dao


import com.google.gson.annotations.SerializedName

//관광지 12 detailIntro 요청 결과값
data class TourIntro (
        @SerializedName("contenttypeid") var contentTypeId: String, //수용 인원
        @SerializedName("contentid") var contentId: String, //수용 인원
        @SerializedName("accomcount") var accomcount: String, //수용 인원
        @SerializedName("chkbabycarriage") var hasBabyCarriage: String, //유모차 대여 여부  //없음
        @SerializedName("chkcreditcard") var subContentId: String, //신용카드 여부
        @SerializedName("chkpet") var isPetPossible: String, //애완동물출입 가능여부~
        @SerializedName("expagerange") var expagerange: String, //체험 가능연령
        @SerializedName("expguide") var expGuide: String, //체험안내
        @SerializedName("heritage1") var heritage1: Int, //세계문화유산 유무 // 0, 1
        @SerializedName("heritage2") var heritage2: Int, //세계자연유산유무 // 0, 1
        @SerializedName("heritage3") var heritage3: Int, //세계 기록 유산 유무 //0, 1
        @SerializedName("infocenter") var infoCenter: String, //문의 및 안내 연락처
        @SerializedName("opendate") var openDate: String, //개장일
        @SerializedName("parking") var parking: String, //주차시설 // 있음
        @SerializedName("useseason") var useSeason: String, //이용시기
        @SerializedName("restdate") var restDate: String, //쉬는날 //"주말 및 공휴일",
        @SerializedName("usetime") var useTime: String //이용시간 //"평일 10:00~14:00" //<br /> 이렇게들 내려와 ㅠㅠ ㅅㅂ
)

//숙박, 여행코스 제외한 타입이라함.... @_@;;;
data class TourDetail (
        @SerializedName("contentid") var contentTypeId: Int,
        @SerializedName("contenttypeid") var contentId: Int,
        @SerializedName("fldgubun") var fldgubun: Int, // 일련번호인데 잘모르겠음 ㅠ
        @SerializedName("infoname") var informationTitle: String, // 제목
        @SerializedName("infotext") var informationDescription: String, // 설명...
        @SerializedName("serialnum") var serialNumber: Int //반복일련번호
)
